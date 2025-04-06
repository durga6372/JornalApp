package durgaproject.jornalapp1.controler;
import   durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.JornalEntryService;
import durgaproject.jornalapp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jornal")
public class JornalEntryControler {
    @Autowired
    private JornalEntryService jornalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getAllJornalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUserName(userName);
        List<JornalEntry> all = user.getJornalEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> creatEntry(@RequestBody JornalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            jornalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJornalEntryById(@PathVariable ObjectId myId ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user = userService.findByUserName(userName);
        List<JornalEntry> collect = user.getJornalEntry().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (collect.isEmpty()){
            Optional<JornalEntry> jornalEntry = jornalEntryService.findById(myId);
            if (jornalEntry.isPresent()) {
                return new ResponseEntity<>(jornalEntry, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletejornalentrybyId(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed = jornalEntryService.dleteById(myId, userName);
        if (removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);




    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JornalEntry newEntry ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUserName(userName);
        List<JornalEntry> collect = user.getJornalEntry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (collect.isEmpty()){
            Optional<JornalEntry> jornalEntry = jornalEntryService.findById(id);
            if (jornalEntry.isPresent()) {
                JornalEntry old = jornalEntryService.findById(id).orElse(null);
                if (old != null) {
                    old.setTitel(newEntry.getTitel() != null && !newEntry.getTitel().equals("") ? newEntry.getTitel() : old.getTitel());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                    jornalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old,HttpStatus.ACCEPTED);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
