package durgaproject.jornalapp1.controler;
import durgaproject.jornalapp1.dto.ChatRequest;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import durgaproject.jornalapp1.service.OpenAIChatService;
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
@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WheatherService wheatherService;
    @Autowired
    private OpenAIChatService chatService;
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
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userRepo.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/greetings/city/{city}")
    public ResponseEntity<?> greetings(@PathVariable String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        wheatherResponse weatherResponse =  wheatherService.getWheather(city);
        return new ResponseEntity<>("Hi " + userName + " wheather feels like " + weatherResponse, HttpStatus.OK);
    }
    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
        try{
            String reply = chatService.getChatResponse(request.getMessage());
            return  ResponseEntity.ok(reply);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Something went wrong.");
        }

    }
}

