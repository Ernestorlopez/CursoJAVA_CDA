package com.example.tpproducts.dto;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Long id, String name, String category, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Constructor sobrecargado por compatibilidad
    public ProductResponseDto(Long id, String name, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.category = "General";
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}