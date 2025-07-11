package durgaproject.jornalapp1.controler;

import durgaproject.jornalapp1.dto.UserDTO;
import durgaproject.jornalapp1.entity.User;
import durgaproject.jornalapp1.repo.UserRepo;
import durgaproject.jornalapp1.service.UserService;
import durgaproject.jornalapp1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        User user=new User();
        if (userDTO.getUserName() == null || userDTO.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password are required"));
        }
        // Check if user already exists (based on userName)
        User existingUser = userService.findByUserName(userDTO.getUserName());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
        }
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setSentimentAnalysis(userDTO.isSentimentAnalysis());
        userService.saveNewUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
                var roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
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