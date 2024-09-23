package com.Myntra.MyntraProject.controller;

import com.Myntra.MyntraProject.models.Product;
import com.Myntra.MyntraProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products (Accessible by any user)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // Get a product by ID (Accessible by any user)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Add a new product (Admin-only access)
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestParam String username, @RequestBody Product product) {
        if (isAdmin(username)) {
            productService.saveProduct(product);
            return ResponseEntity.ok("Product added successfully");
        }
        return ResponseEntity.status(403).body("Access denied: Admins only");
    }

    // Update a product by ID (Admin-only access)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestParam String username, @PathVariable int id, @RequestBody Product updatedProduct) {
        if (isAdmin(username)) {
            Product existingProduct = productService.getProductById(id);
            if (existingProduct != null) {
                updatedProduct.setId(id);
                productService.saveProduct(updatedProduct);
                return ResponseEntity.ok("Product updated successfully");
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(403).body("Access denied: Admins only");
    }

    // Delete a product by ID (Admin-only access)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@RequestParam String username, @PathVariable int id) {
        if (isAdmin(username)) {
            Product product = productService.getProductById(id);
            if (product != null) {
                productService.deleteProduct(id);
                return ResponseEntity.ok("Product deleted successfully");
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(403).body("Access denied: Admins only");
    }

    // Utility method to check if the user is an admin (simplified)
    private boolean isAdmin(String username) {
        return "adminUser".equals(username);
    }
}
