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

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByNameIgnoreCase(name));
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseDto>> getByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(productService.findByPriceBetween(min, max));
    }

    @GetMapping("/top-by-category")
    public ResponseEntity<List<ProductResponseDto>> getTopByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findTopByCategory(category));
    }

    @GetMapping("/search-wildcard")
    public ResponseEntity<List<ProductResponseDto>> searchByNameWildcard(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByNameWildcard(name));
    }

    @GetMapping("/count-by-category")
    public ResponseEntity> countByCategory(@RequestParam String category) {
        return ResponseEntity.ok(Map.of(
                "category", category,
                "totalProducts", productService.countByCategory(category)
        ));
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
    public ResponseEntity<ProductResponseDto> updateProductStock(
            @PathVariable Long id,
            @Valid @RequestBody ProductStockUpdateDto stockDto) {
        return ResponseEntity.ok(productService.updateStock(id, stockDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
