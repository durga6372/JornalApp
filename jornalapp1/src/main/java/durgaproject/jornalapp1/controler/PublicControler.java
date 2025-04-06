package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicControler {
    @Autowired
    private UserService userService;
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
    @GetMapping("/health-check")
    public String HealthCheck(){
        return "ok";
    }

}