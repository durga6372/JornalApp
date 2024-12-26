package durgaproject.jornalapp1.controler;
import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.JornalEntryService;
import durgaproject.jornalapp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/jornal")
public class JornalEntryControler {
    @Autowired
    private JornalEntryService jornalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJornalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JornalEntry> all = user.getJornalEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> creatEntry(@RequestBody JornalEntry myEntry, @PathVariable String userName) {
        try {
            jornalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{userName}{myId}")
    public ResponseEntity<?> getJornalEntryById(@PathVariable ObjectId myId ,@PathVariable String userName) {
        Optional<JornalEntry> jornalEntry = jornalEntryService.findById(myId);
        if (jornalEntry.isPresent()) {
            return new ResponseEntity<>(jornalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deletejornalentrybyId(@PathVariable ObjectId myId,@PathVariable String userName) {
        jornalEntryService.dleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JornalEntry newEntry ,@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        JornalEntry old = jornalEntryService.findById(id).orElse(null);
        if (old != null) {
            old.setTitel(newEntry.getTitel() != null && !newEntry.getTitel().equals("") ? newEntry.getTitel() : old.getTitel());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            jornalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
