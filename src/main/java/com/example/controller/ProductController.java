package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String size) {
        
        // Si ambos vienen nulos o vacíos, devolvemos todos
        if ((category == null || category.isEmpty()) && (size == null || size.isEmpty())) {
            return ResponseEntity.ok(productService.getAllProducts());
        }
        
        return ResponseEntity.ok(productService.getFilteredProducts(category, size));
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(productService.findAllCategories());
    }
    
    @GetMapping("/getSizes")
    public ResponseEntity<List<String>> getSizes() {
        return ResponseEntity.ok(productService.findAllSizes());
    }

    @GetMapping("/getSizesFromCategory")
    public ResponseEntity<List<String>> getSizesFromCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findSizesFromProductCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Long id) {
        Product product = productService.getProductByIdWithComments(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
