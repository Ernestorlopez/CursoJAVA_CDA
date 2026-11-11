package com.example.tpproducts.service;

import com.example.tpproducts.dto.AddCartItemRequestDto;
import com.example.tpproducts.dto.CartItemResponseDto;
import com.example.tpproducts.dto.CartResponseDto;
import com.example.tpproducts.exception.ConflictException;
import com.example.tpproducts.exception.ResourceNotFoundException;
import com.example.tpproducts.model.Cart;
import com.example.tpproducts.model.CartItem;
import com.example.tpproducts.model.Product;
import com.example.tpproducts.repository.CartRepository;
import com.example.tpproducts.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartResponseDto createCart() {
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        return mapToResponseDto(savedCart);
    }

    @Transactional(readOnly = true)
    public CartResponseDto findById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el carrito con id: " + cartId));
        return mapToResponseDto(cart);
    }

    @Transactional
    public CartResponseDto addItemToCart(Long cartId, AddCartItemRequestDto requestDto) {
        // 1. Buscar Cart por cartId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el carrito con id: " + cartId));

        // 2. Buscar Product por productId
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con id: " + requestDto.getProductId()));

        // Regla de conflicto de negocio (409): Verificar stock disponible
        if (product.getStock() != null && product.getStock() < requestDto.getQuantity()) {
            throw new ConflictException("Stock insuficiente para el producto '" + product.getName()
                    + "'. Stock disponible: " + product.getStock() + ", solicitado: " + requestDto.getQuantity());
        }

        // 3. Revisar si el producto ya existe en el carrito para sumar o agregar nuevo
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + requestDto.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(requestDto.getQuantity());
            // Uso del método helper bidireccional obligatorio
            cart.addItem(newItem);
        }

        // 4. Guardar carrito (cascade = CascadeType.ALL sincroniza los items automáticamente)
        Cart savedCart = cartRepository.save(cart);
        return mapToResponseDto(savedCart);
    }

    private CartResponseDto mapToResponseDto(Cart cart) {
        List<CartItemResponseDto> itemsDto = cart.getItems().stream()
                .map(item -> new CartItemResponseDto(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new CartResponseDto(cart.getId(), itemsDto);
    }
}
