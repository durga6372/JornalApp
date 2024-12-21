package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.service.JornalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/jornal")
public class JornalEntryControler {
    @Autowired
    private JornalEntryService jornalEntryService;

    @GetMapping
    public List<JornalEntry> getAll() {

        return jornalEntryService.getAll();
    }

    @PostMapping
    public JornalEntry creatEntry(@RequestBody JornalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        jornalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JornalEntry getjornalentrybyId(@PathVariable ObjectId myId) {
        return jornalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deletejornalentrybyId(@PathVariable ObjectId myId) {
        jornalEntryService.dleteById(myId);
        return true;

    }

    @PutMapping("/id/{id}")
    public JornalEntry updateById(@PathVariable ObjectId id,@RequestBody JornalEntry newEntry) {
        JornalEntry old = jornalEntryService.findById(id).orElse(null);
        if (old != null) {
            old.setTitel(newEntry.getTitel() != null && !newEntry.getTitel().equals("") ? newEntry.getTitel() : old.getTitel());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        jornalEntryService.saveEntry(old);
        return old;
    }
}
