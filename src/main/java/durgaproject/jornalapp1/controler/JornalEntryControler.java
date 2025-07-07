package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.JornalEntryService;
import durgaproject.jornalapp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/jornal")
public class JornalEntryControler {
    @Autowired
    private JornalEntryService jornalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJornalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JornalEntry> all = user.getJornalEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("no content", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> creatEntry(@RequestBody JornalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            jornalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJornalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JornalEntry> collect = user.getJornalEntry().stream().filter(x -> x.getId().equals(myId)).toList();
        if (collect.isEmpty()) {
            Optional<JornalEntry> jornalEntry = jornalEntryService.findById(myId);
            if (jornalEntry.isPresent()) {
                return new ResponseEntity<>(jornalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletejornalentrybyId(@PathVariable String myId) {
        try {
            ObjectId objectId = new ObjectId(myId); // Manual conversion

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            boolean removed = jornalEntryService.dleteById(objectId, userName);
            if (removed) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (IllegalArgumentException e) {
            // Invalid ObjectId format
            return new ResponseEntity<>("Invalid ID format", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JornalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        System.out.println("Received ID: " + id);
        System.out.println("Authenticated user: " + userName);
        System.out.println("User entries: " + user.getJornalEntry());

        List<JornalEntry> collect = user.getJornalEntry().stream()
                .filter(x -> x.getId().toString().equals(id.toString()))
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            return new ResponseEntity<>("Entry not found for this user", HttpStatus.NOT_FOUND);
        }

        JornalEntry old = collect.get(0);
        old.setTitel(newEntry.getTitel() != null && !newEntry.getTitel().isBlank() ? newEntry.getTitel() : old.getTitel());
        old.setContent(newEntry.getContent() != null && !newEntry.getContent().isBlank() ? newEntry.getContent() : old.getContent());
        jornalEntryService.saveEntry(old);

        return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
    }

}
