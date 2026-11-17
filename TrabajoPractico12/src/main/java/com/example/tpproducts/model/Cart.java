package com.example.tpproducts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // mappedBy indica que el dueño de la FK es el atributo 'cart' en CartItem
    // cascade = ALL propaga el guardado del carrito hacia sus items sin CartItemRepository
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    // Metodo helper obligatorio visto en clase para sincronizar la relación bidireccional
    public void addItem(CartItem item) {
        items.add(item);      // Lado de navegación (colección en memoria)
        item.setCart(this);   // Lado dueño (asigna la clave foránea a este carrito)
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
