package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getFiltered(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String color) {
        
        // Llamamos al servicio pasando los criterios
        List<Product> filtered = productService.getFilteredProducts(category, size, color);
        return ResponseEntity.ok(filtered);
    }

}
