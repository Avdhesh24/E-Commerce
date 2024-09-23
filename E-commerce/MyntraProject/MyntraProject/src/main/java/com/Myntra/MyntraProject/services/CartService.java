package com.Myntra.MyntraProject.services;

import com.Myntra.MyntraProject.models.Cart;
import com.Myntra.MyntraProject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

        @Autowired
        private CartRepository cartRepository;

        public Cart addCart(Cart cart) {
            return cartRepository.save(cart);
        }

        public List<Cart> getCarts() {
            return cartRepository.findAll();
        }

        public List<Cart> getCartsByCustomerId(Integer customerId) {
            return cartRepository.findByCustomerId(customerId);
        }

        public void updateCart(Cart cart) {
            cartRepository.save(cart);
        }

        public void deleteCart(Integer cartId) {
            cartRepository.deleteById(cartId);
        }
    }

