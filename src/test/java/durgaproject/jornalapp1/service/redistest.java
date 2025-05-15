package durgaproject.jornalapp1.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class redistest {

    @Autowired
    private RedisTemplate redisTemplate;
   @Disabled
    @Test
    void sendMail(){
        redisTemplate.opsForValue().set("email","durgaputhal@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        System.out.println(email);
        Object salery = redisTemplate.opsForValue().get("salery");
        System.out.println(salery);
        assertNotNull(email, "Email should not be null");




    }
}
