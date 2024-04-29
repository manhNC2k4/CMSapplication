package com.example.cmsapplication.controller;

import com.example.cmsapplication.DTO.UserDTO;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam("username") String username, @RequestParam("password") String password,
                                Model model) {
        Optional<User> user = Optional.ofNullable(userService.getUserByUsername(username));
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                UserDTO userDTO = userService.toDo(user.get());
                Map<String, Object> modelMap = new HashMap<>();
                modelMap.put("user", userDTO);
                modelMap.put("message", "User login successfully");
                return new ResponseEntity<>(modelMap, HttpStatus.OK);
            } else {
                Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("error", "Invalid username or password");
                return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
            }
        } else {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("error", "Invalid username or password");
            return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String username = user.getUsername();
        User newUser = new User();
        if(userService.getUserByEmail(email) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else if(userService.getUserByUsername(username) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            String hashedPassword = passwordEncoder.encode(password);
            newUser.setPassword(hashedPassword);
            newUser.setUsername(username);
            newUser.setEmail(email);
            userService.createUser(newUser);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
    }

}
