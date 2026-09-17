package com.example.tpproducts.dto;

public class ProductStockUpdateDto {
    private Integer quantity;

    public ProductStockUpdateDto() {
    }

    public ProductStockUpdateDto(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
