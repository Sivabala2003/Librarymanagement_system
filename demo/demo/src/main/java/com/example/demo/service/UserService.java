package com.example.demo.service;


import com.example.demo.model.Books;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.MyUser;
import com.example.demo.security.MyUserDetailService;
import com.example.demo.security.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
     UserRepository userRepository;

    @Autowired
    MyUserRepository myUserRepository;


    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("user id" + id + "not found"));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }


    public User updateUser(Long id, User updatedUserDetails) {
        // Find the existing book by ID
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUserDetails.getUsername());
            existingUser.setEmail(updatedUserDetails.getEmail());
            existingUser.setPassword(updatedUserDetails.getPassword());
            // Save updated entity
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new IllegalStateException("user not found with id " + id));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("user  not found with id: " + id);
        }
    }

    }



