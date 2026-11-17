package com.example.tpproducts.controller;

import com.example.tpproducts.dto.ProductRequestDto;
import com.example.tpproducts.dto.ProductResponseDto;
import com.example.tpproducts.dto.ProductStockUpdateDto;
import com.example.tpproducts.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto created = productService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody ProductStockUpdateDto stockDto) {
        return ResponseEntity.ok(productService.updateStock(id, stockDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 1. Buscar por nombre ignorando mayúsculas/minúsculas: GET /api/products/search?name=...
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchByNameIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByNameContainingIgnoreCase(name));
    }

    // 2. Buscar por categoría exacta: GET /api/products/category?category=...
    @GetMapping("/category")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    // 3. Buscar por rango de precios: GET /api/products/price-range?min=...&max=...
    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseDto>> getByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(productService.findByPriceBetween(min, max));
    }

    // 4. Buscar por categoría ordenados de mayor a menor precio (JPQL): GET /api/products/top-by-category?category=...
    @GetMapping("/top-by-category")
    public ResponseEntity<List<ProductResponseDto>> getTopByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findByCategoryOrderByPriceDesc(category));
    }

    // 5. Buscar por nombre usando patrón con comodines (JPQL): GET /api/products/search-pattern?name=...
    @GetMapping("/search-pattern")
    public ResponseEntity<List<ProductResponseDto>> searchByNamePattern(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    // Desafío adicional: Contar productos por categoría: GET /api/products/count-by-category?category=...
    @GetMapping("/count-by-category")
    public ResponseEntity<Object> countByCategory(@RequestParam String category) {
        Long count = productService.countByCategory(category);
        return ResponseEntity.ok(Map.of(
                "category", category,
                "totalProducts", count
        ));
    }
}
