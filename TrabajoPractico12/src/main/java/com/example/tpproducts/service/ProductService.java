package com.example.tpproducts.service;

import com.example.tpproducts.dto.ProductRequestDto;
import com.example.tpproducts.dto.ProductResponseDto;
import com.example.tpproducts.dto.ProductStockUpdateDto;
import com.example.tpproducts.exception.ConflictException;
import com.example.tpproducts.exception.ResourceNotFoundException;
import com.example.tpproducts.model.Product;
import com.example.tpproducts.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long id) {
        Product product = getEntityById(id);
        return toResponseDto(product);
    }

    @Transactional(readOnly = true)
    public Product getEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto requestDto) {
        Product product = new Product(
                requestDto.getName(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getStock()
        );
        Product saved = productRepository.save(product);
        return toResponseDto(saved);
    }

    @Transactional
    public ProductResponseDto updateStock(Long id, ProductStockUpdateDto stockDto) {
        Product product = getEntityById(id);
        product.setStock(product.getStock() + stockDto.getQuantity());
        Product updated = productRepository.save(product);
        return toResponseDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Product product = getEntityById(id);
        productRepository.delete(product);
    }

    // 1. Buscar por nombre ignorando mayúsculas y minúsculas (Query Method)
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByNameContainingIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // 2. Buscar por categoría exacta (Query Method)
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // 3. Buscar por rango de precios (Query Method)
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByPriceBetween(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) > 0) {
            throw new ConflictException("El precio mínimo no puede ser mayor al precio máximo");
        }
        return productRepository.findByPriceBetween(min, max)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // 4. Buscar por categoría ordenados por precio descendente (JPQL)
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByCategoryOrderByPriceDesc(String category) {
        return productRepository.findByCategoryOrderByPriceDesc(category)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // 5. Buscar por nombre usando patrón con comodines (JPQL)
    @Transactional(readOnly = true)
    public List<ProductResponseDto> searchByName(String name) {
        return productRepository.searchByName(name)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // Desafío adicional: Contar productos por categoría
    @Transactional(readOnly = true)
    public Long countByCategory(String category) {
        return productRepository.countByCategory(category);
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
        );
    }
}