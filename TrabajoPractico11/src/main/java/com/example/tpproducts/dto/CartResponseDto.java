package com.example.tpproducts.dto;

import java.util.ArrayList;
import java.util.List;

public class CartResponseDto {

    private Long id;
    private List<CartItemResponseDto> items = new ArrayList<>();

    public CartResponseDto() {
    }

    public CartResponseDto(Long id, List<CartItemResponseDto> items) {
        this.id = id;
        this.items = items != null ? items : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponseDto> items) {
        this.items = items;
    }
}
