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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id: " + id));
        return mapToResponseDto(product);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto requestDto) {
        // Regla de negocio conflictiva (409): producto con nombre duplicado
        if (productRepository.existsByName(requestDto.getName())) {
            throw new ConflictException("Ya existe un producto con el nombre: " + requestDto.getName());
        }

        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        product.setStock(requestDto.getStock());

        Product savedProduct = productRepository.save(product);
        return mapToResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto updateStock(Long id, ProductStockUpdateDto stockDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id: " + id));

        product.setStock(stockDto.getStock());
        Product updatedProduct = productRepository.save(product);
        return mapToResponseDto(updatedProduct);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el producto con id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponseDto mapToResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}