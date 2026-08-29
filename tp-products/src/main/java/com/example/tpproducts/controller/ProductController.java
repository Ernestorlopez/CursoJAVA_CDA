package com.example.tpproducts.controller;

import com.example.tpproducts.dto.ProductRequestDto;
import com.example.tpproducts.dto.ProductResponseDto;
import com.example.tpproducts.dto.ProductStockUpdateDto;
import com.example.tpproducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        Optional<ProductResponseDto> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto created = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(
            @PathVariable Long id,
            @RequestBody ProductStockUpdateDto stockUpdateDto) {

        if (stockUpdateDto.getQuantity() == null || stockUpdateDto.getQuantity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cantidad debe ser mayor a cero.");
        }

        Optional<ProductResponseDto> updated = productService.addStock(id, stockUpdateDto.getQuantity());
        if (updated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con ID " + id + " no encontrado.");
        }

        return ResponseEntity.ok(updated.get());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }
}
