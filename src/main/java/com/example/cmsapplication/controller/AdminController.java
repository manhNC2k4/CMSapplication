package com.example.cmsapplication.controller;

import com.example.cmsapplication.DTO.RegisterRequest;
import com.example.cmsapplication.config.WebSecurityConfig;
import com.example.cmsapplication.model.User;
import com.example.cmsapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("getAllUsers");
        List<User> users = userService.getAllUsers();
        logger.info("getAllUsers successfull...");
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody RegisterRequest request) {
        try {
            if(userService.getUserByEmail(request.getEmail()).isPresent()) {
                return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            } else if(userService.getUserByUsername(request.getUsername()).isPresent()) {
                return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            } else {
                String hashedPassword = passwordEncoder.encode(request.getPassword());
                User newUser = new User( request.getUsername(), hashedPassword, request.getEmail());
                userService.createUser(newUser);
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
