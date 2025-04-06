package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
@Autowired
    private UserRepo userRepo;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
public  void saveUser (User user){  userRepo.save(user);
}
//Logger logger=LoggerFactory.getLogger(UserService.class);
public boolean saveNewUser(User user) {
   try {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRolls(Arrays.asList("user"));
       userRepo.save(user);
       return true;
   }
   catch ( Exception e){
       log.info("an error occurred",e);
//       logger.info("hello",e);
       return false;
   }
}
public List<User > getAll(){return userRepo.findAll();
}
public Optional<User> findByUserId(ObjectId id){
    return userRepo.findById(id);
}
public void dleteByid(ObjectId id){
    userRepo.deleteById(id);
}
public  User findByUserName(String userName){
    return userRepo.findByUserName( userName);
}
}
