package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

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

    public List<Product> getFilteredProducts(String category, String size) {
        if (category != null && size != null) {
            return productRepository.findByCategoryAndSize(category, size);
        }
        if (category != null) {
            return productRepository.findByCategoryIgnoreCase(category);
        }
        if (size != null) {
            return productRepository.findBySize(size);
        }
        return productRepository.findAll();
    }

    public List<String> findAllCategories() {
        return productRepository.findAllCategories();
    }

    public List<String> findAllSizes() {
        return productRepository.findAllSizes();
    }

    public List<String> findSizesFromProductCategory(String category) {
        return productRepository.findSizesFromProductCategory(category);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}
