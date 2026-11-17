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

    // 1. Buscar productos por nombre ignorando mayúsculas y minúsculas (Query Method)
    List<Product> findByNameContainingIgnoreCase(String name);

    // 2. Buscar productos por categoría exacta (Query Method)
    List<Product> findByCategory(String category);

    // 3. Buscar productos por rango de precios (Query Method)
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // 4. Buscar productos por categoría ordenados por precio descendente (JPQL)
    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price DESC")
    List<Product> findByCategoryOrderByPriceDesc(@Param("category") String category);

    // 5. Buscar productos por nombre usando patrón con comodines (JPQL)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    // Desafío adicional: Contar cuántos productos hay por categoría (Query Method)
    Long countByCategory(String category);
}
