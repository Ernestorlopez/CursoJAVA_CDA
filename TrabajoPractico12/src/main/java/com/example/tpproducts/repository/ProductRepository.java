package com.example.tpproducts.repository;

import com.example.tpproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    // 1. Query method: buscar por nombre ignorando mayúsculas y minúsculas
    List<Product> findByNameContainingIgnoreCase(String name);

    // 2. Query method: buscar por categoría exacta
    List<Product> findByCategory(String category);

    // 3. Query method: buscar por rango de precios
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // 4. JPQL: buscar por categoría ordenados por precio descendente
    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price DESC")
    List<Product> findByCategoryOrderByPriceDesc(@Param("category") String category);

    // 5. JPQL: buscar por nombre usando patrón con comodines
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    // Desafío adicional: contar productos por categoría
    Long countByCategory(String category);
}