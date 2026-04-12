package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getFilteredProducts(String category, String size, String color) {
    if (category != null && size != null) {
        return productRepository.findByCategoryAndSize(category, size);
    }
    if (category != null) {
        return productRepository.findByCategory(category);
    }
    if (size != null) {
        return productRepository.findBySize(size);
    }
    return productRepository.findAll();
}
}