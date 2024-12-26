package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.UserService;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService ;
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try {
            List <User> user= userService.getAll();
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user );

    }
    public boolean deleteById(@PathVariable ObjectId id){
         userService.dleteById(id);
         return true;
    }
    @PutMapping("{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName ,@RequestBody User user){
          User username=userService.findByUserName(userName);
          if (username!=null){
              username.setUserName(user.getUserName());
              username.setPassword(user.getPassword());
              userService.saveUser(username);
          }
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
