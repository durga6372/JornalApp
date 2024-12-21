package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.entity.JornalEntry;
import durgaproject.jornalapp1.repo.JornalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JornalEntryService {
@Autowired
    private JornalEntryRepo jornalEntryRepo;
public  void saveEntry (JornalEntry jornalEntry){
    jornalEntryRepo.save(jornalEntry);
}
public List<JornalEntry > getAll(){return jornalEntryRepo.findAll();
}
public Optional<JornalEntry> findById(ObjectId id){
    return jornalEntryRepo.findById(id);
}
public void dleteById(ObjectId id){
     jornalEntryRepo.deleteById(id);
}

}
