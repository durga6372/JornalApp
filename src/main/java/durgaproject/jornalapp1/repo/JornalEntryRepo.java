package durgaproject.jornalapp1.repo;
import durgaproject.jornalapp1.entity.JornalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface JornalEntryRepo extends MongoRepository<JornalEntry,Object> {

}
