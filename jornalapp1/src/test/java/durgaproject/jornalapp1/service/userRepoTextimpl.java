package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.repo.userReposiratoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class userRepoTextimpl {
    @Autowired
    public userReposiratoryImpl userReposiratory;
    @Test
    public void testNewUser(){
        userReposiratory.getUserforSA();
    }
}
