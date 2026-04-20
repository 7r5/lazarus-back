package com.example.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.CommentService;
import com.example.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("GET /api/products/{id} devuelve producto cuando existe")
    void getProductByIdReturnsProductWhenExists() throws Exception {
        Product product = new Product();
        product.setId(5L);
        product.setName("Zapatillas Deportivas");
        product.setBrand("Lazarus Style");
        product.setDescription("Zapatillas cómodas para running");
        product.setCategory("Deportivo");
        product.setPrice(699.0);
        product.setPriceWithDiscount(649.0);
        product.setSize("42");
        product.setColor("Blanco");
        product.setStock(15);
        product.setImageUrl("https://example.com/zapatillas.jpg");

        given(productService.getProductById(5L)).willReturn(product);

        mockMvc.perform(get("/api/products/5").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(5))
            .andExpect(jsonPath("$.name").value("Zapatillas Deportivas"))
            .andExpect(jsonPath("$.category").value("Deportivo"))
            .andExpect(jsonPath("$.price").value(699.0));
    }

   /*  @Test
    @DisplayName("GET /api/products/{id} devuelve producto con comentarios cuando existe")
    void getProductByIdReturnsProductWhenExistsWithComments() throws Exception {
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setContent("¡Me encantan estas zapatillas!");
        comment1.setType("Product");
        comment1.setProduct(null); // Para evitar referencias circulares en este test

        Product product = new Product();
        product.setId(5L);
        product.setName("Zapatillas Deportivas");
        product.setBrand("Lazarus Style");
        product.setDescription("Zapatillas cómodas para running");
        product.setCategory("Deportivo");
        product.setPrice(699.0);
        product.setPriceWithDiscount(649.0);
        product.setSize("42");
        product.setColor("Blanco");
        product.setStock(15);
        product.setImageUrl("https://example.com/zapatillas.jpg");
        List<Comment> comentarios = new ArrayList<>();
        comentarios.add(comment1);
        product.setComments(comentarios);


        given(productService.getProductByIdWithComments(5L)).willReturn(product);

        mockMvc.perform(get("/api/products/5").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(5))
            .andExpect(jsonPath("$.name").value("Zapatillas Deportivas"))
            .andExpect(jsonPath("$.category").value("Deportivo"))
            .andExpect(jsonPath("$.price").value(699.0))
            .andExpect(jsonPath("$.comments").isArray())
            .andExpect(jsonPath("$.comments").isNotEmpty());

    } */

    @Test
    @DisplayName("GET /api/products/{id} devuelve 404 cuando no existe")
    void getProductByIdReturnsNotFoundWhenMissing() throws Exception {
        given(productService.getProductById(anyLong())).willReturn(null);

        mockMvc.perform(get("/api/products/999").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}
