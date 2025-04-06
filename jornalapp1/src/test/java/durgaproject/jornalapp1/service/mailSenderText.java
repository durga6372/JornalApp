package durgaproject.jornalapp1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class mailSenderText {
  @Autowired
  private  EmailService emailService;
    @Test
    public  void  sendMAil(){
        emailService.sendMail("durgaputhal@gmail.com","hello","How are you");

    }

}
