package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import durgaproject.jornalapp1.service.UserService;
import durgaproject.jornalapp1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicControler {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if (user.getUserName() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password are required"));
        }
        // Check if user already exists (based on userName)
        User existingUser = userService.findByUserName(user.getUserName());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
        }

        user.setId(null); // Let Mongo generate it
        user.setJornalEntry(new ArrayList<>());
        if (user.getRolls() == null) user.setRolls(new ArrayList<>());

        userService.saveNewUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
                var roles = userDetails.getAuthorities().stream()
                        .map(auth -> auth.getAuthority())
                        .toList();

                String token = jwtUtil.generateToken(user.getUserName(), roles);

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return response;
            }

            throw new RuntimeException("Authentication failed");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid username or password");
        }
    }


    @GetMapping("/health-check")
    public String HealthCheck(){
        return "ok";
    }

}