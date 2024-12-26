package durgaproject.jornalapp1.repo;
import durgaproject.jornalapp1.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
