package com.Myntra.MyntraProject.services;

import com.Myntra.MyntraProject.models.Product;
import com.Myntra.MyntraProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Business logic to save a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Fetch all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Fetch a product by its ID
    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // Return the product if found, otherwise null
    }

    public void deleteProduct(int id) {
    }

    // Other service methods can go here
}
