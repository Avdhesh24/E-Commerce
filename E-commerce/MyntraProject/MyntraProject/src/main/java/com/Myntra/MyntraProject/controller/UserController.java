package com.Myntra.MyntraProject.controller;

import com.Myntra.MyntraProject.models.User;
import com.Myntra.MyntraProject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User Registration
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        boolean exists = userService.checkUserExists(user.getUsername());

        if (exists) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        user.setRole("ROLE_USER");  // Basic role for the user
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        User user = userService.checkLogin(loginUser.getUsername(), loginUser.getPassword());

        if (user != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // Utility method to check if the user is an admin by username
    private boolean isAdmin(String username) {
        User user = userService.getUserByUsername(username);
        return user != null && "ROLE_ADMIN".equals(user.getRole());
    }
}
