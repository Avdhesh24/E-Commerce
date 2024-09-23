package com.Myntra.MyntraProject.services;

import com.Myntra.MyntraProject.models.Order;
import com.Myntra.MyntraProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Method to create or save an order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Method to fetch all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Method to fetch a single order by ID
    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Method to delete an order by ID
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

}
