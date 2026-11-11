package com.example.tpproducts.service;

import com.example.tpproducts.dto.AddCartItemRequestDto;
import com.example.tpproducts.dto.CartResponseDto;
import com.example.tpproducts.model.Cart;
import com.example.tpproducts.model.CartItem;
import com.example.tpproducts.model.Product;
import com.example.tpproducts.repository.CartRepository;
import com.example.tpproducts.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartResponseDto addItemToCart(Long cartId, AddCartItemRequestDto requestDto) {
        // 1. Buscar Cart por cartId (404 simple si no existe)
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado con id: " + cartId));

        // 2. Buscar Product por productId (404 simple si no existe)
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + requestDto.getProductId()));

        // 3. Crear CartItem y asociarlo mediante el helper cart.addItem(item)
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(requestDto.getQuantity());
        cart.addItem(cartItem);

        // 4. Guardar carrito (cascade = CascadeType.ALL persiste el CartItem automáticamente)
        Cart savedCart = cartRepository.save(cart);

        // Retornar DTO de respuesta actualizado
        return mapToResponseDto(savedCart);
    }

    // Método de mapeo a DTO (adaptar a los campos de tu CartResponseDto)
    private CartResponseDto mapToResponseDto(Cart cart) {
        // Mapea la entidad Cart y su lista cart.getItems() hacia CartResponseDto
        // Retornando los datos del carrito con los ítems y productos asociados
        return new CartResponseDto(cart);
    }
}
