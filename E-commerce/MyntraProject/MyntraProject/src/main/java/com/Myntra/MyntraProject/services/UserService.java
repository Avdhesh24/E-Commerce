package com.Myntra.MyntraProject.services;

import com.Myntra.MyntraProject.models.User;
import com.Myntra.MyntraProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to get all users
    public List<User> getUsers() {
        return this.userRepository.findAll();  // Correct method name
    }

    // Method to add a new user
    public User addUser(User user) {
        try {
            return this.userRepository.save(user);  // Corrected to 'save'
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation
            throw new RuntimeException("Username already exists");
        }
    }

    // Method to check user login by username and password
    public User checkLogin(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);  // Correct method name
    }

    // Method to check if a user exists by username
    public boolean checkUserExists(String username) {
        return this.userRepository.existsByUsername(username);  // Correct method name
    }

    // Method to fetch user by username
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);  // Correct method name
    }
}
