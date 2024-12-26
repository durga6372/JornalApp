package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
@Autowired
    private UserRepo userRepo;
public  void saveUser (User user){  userRepo.save(user);
}
public List<User > getAll(){return userRepo.findAll();
}
public Optional<User> findByUserId(ObjectId id){
    return userRepo.findById(id);
}
public void dleteById(ObjectId id){
    userRepo.deleteById(id);
}
public  User findByUserName(String userName){
    return userRepo.findByUserName( userName);
}
}
