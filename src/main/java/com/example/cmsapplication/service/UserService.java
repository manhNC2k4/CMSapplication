package com.example.cmsapplication.service;

import com.example.cmsapplication.DTO.UserDTO;
import com.example.cmsapplication.model.User;
import com.example.cmsapplication.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User createUser(User user) {
        return userRepo.save(user);
    }
    public void deleteUser(Long userID) {
        userRepo.deleteById(userID);
    }
    public User getUserById(Long userID) {
        return userRepo.findById(userID).get();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserDTO toDo(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        return userDTO;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).get();
    }
}
