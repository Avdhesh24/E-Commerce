package com.Myntra.MyntraProject.repository;

import com.Myntra.MyntraProject.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Custom query methods can be defined here if needed
    List<Cart> findByCustomerId(Integer customerId);
}

