package durgaproject.jornalapp1.service;
import durgaproject.jornalapp1.model.SentimentData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;

   @KafkaListener(topics = "weekly-sentiment",groupId = "weekly-sentiment-group")
   public void consumer(SentimentData sentimentData){
      sendEmail(sentimentData);
    }

    private void sendEmail(SentimentData sentimentData) {
        emailService.sendMail(sentimentData.getEmail(), "sentiment analysis of the week",sentimentData.getSentiment());
    }

}
