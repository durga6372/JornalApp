package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.JornalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JornalEntryService {
    @Autowired
    private JornalEntryRepo jornalEntryRepo;
    @Autowired
    private UserService userService;


    public void saveEntry(JornalEntry jornalEntry, String userName) {
        jornalEntry.setDate(LocalDateTime.now());
        User user = userService.findByUserName(userName);
        JornalEntry saved = jornalEntryRepo.save(jornalEntry);
        user.getJornalEntry().add(saved);
        userService.saveNewUser(user);
    }  

    public void saveEntry(JornalEntry jornalEntry) {
        jornalEntry.setDate(LocalDateTime.now());
        JornalEntry saved = jornalEntryRepo.save(jornalEntry);

    }

    public List<JornalEntry> getAll() {
        return jornalEntryRepo.findAll();
    }

    public Optional<JornalEntry> findById(ObjectId id) {
        return jornalEntryRepo.findById(id);
    }

    @Transactional
    public boolean dleteById(ObjectId id, String userName) {
        boolean remove=false;
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJornalEntry().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                jornalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("an error occurred " + e);

        }
        return remove;
    }
}