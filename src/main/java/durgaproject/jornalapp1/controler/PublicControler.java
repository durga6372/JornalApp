package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.service.UserService;
import durgaproject.jornalapp1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicControler {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // Fetch roles from authenticated user details
            var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            var roles = userDetails.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

            // Generate token with roles
            String token = jwtUtil.generateToken(user.getUserName(),roles);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @GetMapping("/health-check")
    public String HealthCheck(){
        return "ok";
    }

}