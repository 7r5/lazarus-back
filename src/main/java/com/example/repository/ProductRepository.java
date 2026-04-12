package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Filtrar por categoría (Exacto)
    List<Product> findByCategory(String category);

    // Filtrar por talla
    List<Product> findBySize(String size);

    // Filtrar por color
    List<Product> findByColor(String color);

    // FILTRO COMBINADO: Muy útil para el frontend
    // Ejemplo: /api/clothes/filter?category=Urbano&size=L
    List<Product> findByCategoryAndSize(String category, String size);

    // Buscar por nombre (para la barra de búsqueda del e-commerce)
    List<Product> findByNameContainingIgnoreCase(String name);
}