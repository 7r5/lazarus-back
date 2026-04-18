package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    // Filtrar por categoría (Exacto)
    List<Product> findByCategoryIgnoreCase(String category);

    // Filtrar por talla
    List<Product> findBySize(String size);

    // Filtrar por color
    List<Product> findByColor(String color);

    // FILTRO COMBINADO: Muy útil para el frontend
    // Ejemplo: /api/clothes/filter?category=Urbano&size=L
    List<Product> findByCategoryAndSize(String category, String size);

    // Buscar por nombre (para la barra de búsqueda del e-commerce)
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();

    @Query("SELECT DISTINCT p.size FROM Product p")
    List<String> findAllSizes();

    // Agrega un metodo que regrese las tallas disponibles para una categoria dada
    @Query("SELECT DISTINCT p.size FROM Product p WHERE p.category = :category")
    List<String> findSizesFromProductCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.comments WHERE p.id = :id")
    Product findByIdWithComments(@Param("id") Long id);

}