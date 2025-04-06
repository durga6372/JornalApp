package durgaproject.jornalapp1.controler;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import durgaproject.jornalapp1.service.UserService;
import durgaproject.jornalapp1.service.WheatherService;
import durgaproject.jornalapp1.wheatherentity.wheatherResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WheatherService wheatherService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();
           User username = userService.findByUserName(userName);
           username.setUserName(user.getUserName());
           username.setPassword(user.getPassword());
           userService.saveNewUser(user);
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userRepo.deleteByUserName(userName);
        return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userRepo.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/greetings")
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        wheatherResponse weatherResponse =  wheatherService.getWheather("mumbai"); // Use the instance here
//        String greeting="";
//        if (weatherResponse!=null){
//          greeting="wheathet feels like";
//        }
        return new ResponseEntity<>("Hi " + userName + "wheather feels like " + weatherResponse, HttpStatus.OK);

    }


}

