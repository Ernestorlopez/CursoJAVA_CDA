package com.example.tpproducts.controller;

import com.example.tpproducts.dto.AddCartItemRequestDto;
import com.example.tpproducts.dto.CartResponseDto;
import com.example.tpproducts.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponseDto> addItem(
            @PathVariable Long cartId,
            @Valid @RequestBody AddCartItemRequestDto requestDto) {
        CartResponseDto updatedCart = cartService.addItemToCart(cartId, requestDto);
        return ResponseEntity.ok(updatedCart);
    }
}
