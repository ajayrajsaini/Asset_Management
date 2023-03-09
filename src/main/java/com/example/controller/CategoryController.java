package com.example.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.*;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
private final CategoryService categoryService;
public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
}

@GetMapping("")
public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
}

@GetMapping("/{id}")
public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Optional<Category> categoryOptional = categoryService.getCategoryById(id);
    return categoryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

@PostMapping("")
public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category savedCategory = categoryService.saveCategory(category);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedCategory.getId()).toUri();
    return ResponseEntity.created(location).body(savedCategory);
}

@PutMapping("/{id}")
public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    Optional<Category> categoryOptional = categoryService.getCategoryById(id);
    if (categoryOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    category.setId(id);
    Category updatedCategory = categoryService.saveCategory(category);
    return ResponseEntity.ok(updatedCategory);
}

@DeleteMapping("/{id}")
public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
    Optional<Category> categoryOptional = categoryService.getCategoryById(id);
    if (categoryOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    Category category = categoryOptional.get();
    categoryService.deleteCategory(category);
    return ResponseEntity.noContent().build();
}
}