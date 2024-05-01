package com.example.cmsapplication.controller;

import com.example.cmsapplication.DTO.LoginRequest;
import com.example.cmsapplication.DTO.LoginResponse;
import com.example.cmsapplication.DTO.RegisterRequest;
import com.example.cmsapplication.model.User;
import com.example.cmsapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginRegister {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public LoginRegister(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.getUserByUsername(request.getUsername());
        if (user.isPresent()) {
            if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                LoginResponse response = new LoginResponse(user.get().getUsername(), user.get().getEmail(), user.get().getId(), user.get().getIsAdmin());
                response.setMessage("User login successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("error", "Invalid username or password");
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
            }
        } else {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("error", "Invalid username or password");
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
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

}
