package durgaproject.jornalapp1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class jornalServicetest {
    @Autowired
    private JornalEntryService jornalEntryService;
    @Test
    public void text(){
       assertNotNull(jornalEntryService.getAll());
    }
}
