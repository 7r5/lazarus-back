package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Lazarus Shop API",
        version = "1.0",
        description = "API REST para la tienda de ropa Lazarus Shop"
    )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            // Seed products if empty
            if (productRepository.count() == 0) {
                Product p1 = new Product();
                p1.setName("Playera Oversize");
                p1.setBrand("Lazarus Style");
                p1.setDescription("Playera cómoda para uso diario");
                p1.setCategory("Urbano");
                p1.setPrice(299.0);
                p1.setPriceWithDiscount(249.0);
                p1.setSize("M");
                p1.setColor("Negro");
                p1.setStock(10);
                p1.setImageUrl("https://example.com/playera.jpg");
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setName("Pantalón Cargo");
                p2.setBrand("Lazarus Style");
                p2.setDescription("Pantalón resistente con bolsillos");
                p2.setCategory("Urbano");
                p2.setPrice(499.0);
                p2.setPriceWithDiscount(449.0);
                p2.setSize("L");
                p2.setColor("Gris");
                p2.setStock(5);
                p2.setImageUrl("https://example.com/pantalon.jpg");
                productRepository.save(p2);

                Product p3 = new Product();
                p3.setName("Gorra Snapback");
                p3.setBrand("Lazarus Style");
                p3.setDescription("Gorra ajustable para estilo urbano");
                p3.setCategory("Accesorios");
                p3.setPrice(199.0);
                p3.setPriceWithDiscount(179.0);
                p3.setSize("Única");
                p3.setColor("Negro");
                p3.setStock(20);
                p3.setImageUrl("https://example.com/gorra.jpg");
                productRepository.save(p3);

                Product p4 = new Product();
                p4.setName("Mochila Urbana");
                p4.setBrand("Lazarus Style");
                p4.setDescription("Mochila espaciosa para ciudad");
                p4.setCategory("Accesorios");
                p4.setPrice(399.0);
                p4.setPriceWithDiscount(359.0);
                p4.setSize("Única");
                p4.setColor("Azul");
                p4.setStock(8);
                p4.setImageUrl("https://example.com/mochila.jpg");
                productRepository.save(p4);

                Product p5 = new Product();
                p5.setName("Zapatillas Deportivas");
                p5.setBrand("Lazarus Style");
                p5.setDescription("Zapatillas cómodas para running");
                p5.setCategory("Deportivo");
                p5.setPrice(699.0);
                p5.setPriceWithDiscount(649.0);
                p5.setSize("42");
                p5.setColor("Blanco");
                p5.setStock(15);
                p5.setImageUrl("https://example.com/zapatillas.jpg");
                productRepository.save(p5);
            }
        };
    }
}
