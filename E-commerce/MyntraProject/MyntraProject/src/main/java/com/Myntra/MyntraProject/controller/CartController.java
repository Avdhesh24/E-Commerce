package com.Myntra.MyntraProject.controller;

import com.Myntra.MyntraProject.models.Cart;
import com.Myntra.MyntraProject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get all carts
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getCarts();
    }

    // Get carts by customer ID
    @GetMapping("/customer/{customerId}")
    public List<Cart> getCartsByCustomerId(@PathVariable Integer customerId) {
        return cartService.getCartsByCustomerId(customerId);
    }

    // Add a new cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart newCart = cartService.addCart(cart);
        return ResponseEntity.ok(newCart);
    }

    // Update an existing cart
    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer cartId, @RequestBody Cart cart) {
        cart.setId(cartId);
        cartService.updateCart(cart);
        return ResponseEntity.ok(cart);
    }

    // Delete a cart by ID
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
