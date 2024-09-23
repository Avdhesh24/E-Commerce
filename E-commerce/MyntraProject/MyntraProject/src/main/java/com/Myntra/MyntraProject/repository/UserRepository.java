package com.Myntra.MyntraProject.repository;

import com.Myntra.MyntraProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Custom method to find a user by username
    User findByUsername(String username);

    // Custom method to check if a user exists by username
    boolean existsByUsername(String username);

    // Custom method to check login credentials
    User findByUsernameAndPassword(String username, String password);
}
