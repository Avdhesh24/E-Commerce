package com.Myntra.MyntraProject.services;

import com.Myntra.MyntraProject.models.Category;
import com.Myntra.MyntraProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Add a new category
     * @param name
     * @return added category
     */
    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    /**
     * Get all categories
     * @return list of categories
     */
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Delete a category by ID
     * @param id
     * @return true if deleted
     */
    public Boolean deleteCategory(int id) {
        categoryRepository.deleteById(id);
        return true;
    }

    /**
     * Update a category
     * @param id
     * @param name
     * @return updated category
     */
    public Category updateCategory(int id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(name);
        return categoryRepository.save(category);
    }

    /**
     * Get a category by ID
     * @param id
     * @return category
     */
    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
