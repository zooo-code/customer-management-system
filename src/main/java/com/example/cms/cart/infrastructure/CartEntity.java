package com.example.cms.cart.infrastructure;


import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.infrastructure.CartItemEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity   {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name ="count")
    private Integer count;

    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToMany(mappedBy = "cartEntity")
    private List<CartItemEntity> cartItemEntities = new ArrayList<>();

    private Long createdAt;

    @Builder
    public CartEntity(Long id, Integer count, Integer totalPrice, List<CartItemEntity> cartItemEntities, Long createdAt) {
        this.id = id;
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItemEntities = cartItemEntities;
        this.createdAt = createdAt;
    }
    public static CartEntity from(Cart cart){
        CartEntity cartEntity = new CartEntity();
        cartEntity.cartItemEntities = cart.getCartItems()
                .stream()
                .map(CartItemEntity::from)
                .collect(Collectors.toList());

        cartEntity.count = cart.getCount();
        cartEntity.totalPrice = cart.getTotalPrice();
        return cartEntity;
    }

    public Cart toModel(){
        return Cart.builder()
                .id(id)
                .cartItems(cartItemEntities.stream()
                        .map(CartItemEntity::toModel).collect(Collectors.toList()))
                .totalPrice(totalPrice)
                .count(count)
                .createdAt(createdAt)
                .build();
    }



}
