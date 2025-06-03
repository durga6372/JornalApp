package durgaproject.jornalapp1.sheduler;
import durgaproject.jornalapp1.Enum.Sentiment;
import durgaproject.jornalapp1.cache.AppCache;
import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.model.SentimentData;
import durgaproject.jornalapp1.repo.userReposiratoryImpl;
import durgaproject.jornalapp1.service.EmailService;
import durgaproject.jornalapp1.service.senmentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class UserSheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private userReposiratoryImpl useReposiratory;
    @Autowired
    private senmentAnalysisService senmentAnalysisService;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private AppCache appCache;

    //  @Scheduled(cron = "0 0 9 * *SUN")
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendMail() {
        List<User> user = useReposiratory.getUserforSA();
        for (User users : user) {
            List<JornalEntry> jornalEntry = users.getJornalEntry();
            List<Sentiment> sentiments = jornalEntry.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> SentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    SentimentCounts.put(sentiment, SentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment MostFrequentSentiMent = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : SentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    MostFrequentSentiMent = entry.getKey();
                }
                if (MostFrequentSentiMent != null) {
                    SentimentData sentimentData = SentimentData.builder().email(users.getEmail()).sentiment("Sentiment for last 7 days " + MostFrequentSentiMent).build();
                    try {
                        kafkaTemplate.send("weekly-sentiments", sentimentData.getEmail(), sentimentData);
                    } catch (Exception e) {
                        emailService.sendMail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
                    }
                }
            }
        }
    }
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.refresh();
    }
}
