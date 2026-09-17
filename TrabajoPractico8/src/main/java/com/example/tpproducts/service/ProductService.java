package com.example.tpproducts.service;

import com.example.tpproducts.dto.ProductRequestDto;
import com.example.tpproducts.dto.ProductResponseDto;
import com.example.tpproducts.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ProductResponseDto> getAllProducts() {
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDto> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this::mapToResponseDto);
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product(
                idGenerator.getAndIncrement(),
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getStock()
        );
        products.add(product);
        return mapToResponseDto(product);
    }

    public Optional<ProductResponseDto> addStock(Long id, Integer quantity) {
        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStock(product.getStock() + quantity);
            return Optional.of(mapToResponseDto(product));
        }

        return Optional.empty();
    }

    public List<ProductResponseDto> searchByName(String name) {
        return products.stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ProductResponseDto mapToResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}