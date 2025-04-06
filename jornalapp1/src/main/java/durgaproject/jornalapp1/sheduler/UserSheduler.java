package durgaproject.jornalapp1.sheduler;

import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.userReposiratoryImpl;
import durgaproject.jornalapp1.service.EmailService;
import durgaproject.jornalapp1.service.senmentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserSheduler {
   @Autowired
   private EmailService emailService;
   @Autowired
   private userReposiratoryImpl useReposiratory;
   @Autowired
   private senmentAnalysisService senmentAnalysisService;
//  @Scheduled(cron = "0 0 9 * *SUN")
  @Scheduled(cron = "0 * * ? * *")
   public void fetchUsersAndSendMail(){
        List<User> user = useReposiratory.getUserforSA();
        for (User users:user){
            List<JornalEntry> jornalEntry = users.getJornalEntry();
            List<String> filtered = jornalEntry.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList());
            String entry = String.join("", filtered);
            String sentiment = senmentAnalysisService.getSentiment(entry);
//          emailService.sendMail(users.getEmail(),"sentiment for last Sevendays",sentiment);
        }


    }

}
