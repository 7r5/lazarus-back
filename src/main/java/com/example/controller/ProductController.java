package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Endpoints para gestionar productos de la tienda")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    })
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del producto inválidos")
    })
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping("/filter")
    @Operation(summary = "Filtrar productos", description = "Filtra productos por categoría y/o talla")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos filtrados obtenidos exitosamente")
    })
    public ResponseEntity<List<Product>> filterProducts(
        @Parameter(description = "Categoría del producto") @RequestParam(required = false) String category,
        @Parameter(description = "Talla del producto") @RequestParam(required = false) String size) {
        
        // Si ambos vienen nulos o vacíos, devolvemos todos
        if ((category == null || category.isEmpty()) && (size == null || size.isEmpty())) {
            return ResponseEntity.ok(productService.getAllProducts());
        }
        
        return ResponseEntity.ok(productService.getFilteredProducts(category, size));
    }

    @GetMapping("/getCategories")
    @Operation(summary = "Obtener categorías", description = "Devuelve una lista de todas las categorías disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    })
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(productService.findAllCategories());
    }
    
    @GetMapping("/getSizes")
    @Operation(summary = "Obtener tallas", description = "Devuelve una lista de todas las tallas disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tallas obtenida exitosamente")
    })
    public ResponseEntity<List<String>> getSizes() {
        return ResponseEntity.ok(productService.findAllSizes());
    }

    @GetMapping("/getSizesFromCategory")
    @Operation(summary = "Obtener tallas por categoría", description = "Devuelve las tallas disponibles para una categoría específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tallas obtenida exitosamente")
    })
    public ResponseEntity<List<String>> getSizesFromCategory(@Parameter(description = "Categoría del producto") @RequestParam String category) {
        return ResponseEntity.ok(productService.findSizesFromProductCategory(category));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles de producto", description = "Devuelve los detalles de un producto específico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles del producto obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Product> getProductDetails(@Parameter(description = "ID del producto") @PathVariable Long id) {
        Product product = productService.getProductByIdWithComments(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
