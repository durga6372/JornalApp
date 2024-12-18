package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.JornalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jornal")
public class JornalEntryControler {
    private Map<Long,JornalEntry> jornalEntries= new HashMap<>();
@GetMapping
public List<JornalEntry> getAll(){
   return new ArrayList<>(jornalEntries.values());
}
@PostMapping
public boolean creatEntry(@RequestBody JornalEntry myEntry){
jornalEntries.put(myEntry.getId(), myEntry);
return true;
}
@GetMapping("id/{myId}")
public JornalEntry getjornalentrybyId(@PathVariable long myId){
   return jornalEntries.get(myId);

}
@DeleteMapping("id/{myId}")
public JornalEntry deletejornalentrybyId(@PathVariable long myId){
        return jornalEntries.get(myId);

}
@PutMapping("/id/{id}")

public JornalEntry updateJornalentryByid(@PathVariable long id,@RequestBody JornalEntry myEntry){
   return jornalEntries.put(id,myEntry);
    }
}
