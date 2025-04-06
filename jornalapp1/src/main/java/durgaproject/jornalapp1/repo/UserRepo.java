package durgaproject.jornalapp1.repo;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.entity.JornalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    Boolean deleteByUserName(String userName);



}


