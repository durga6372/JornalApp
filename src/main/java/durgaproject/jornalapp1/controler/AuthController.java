package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/exists")
    public Map<String, Boolean> userExists() {
        boolean exists = userService.anyUserExists();
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
}

