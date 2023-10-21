package com.example.cms.cartitem.infrastructure;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.infrastructure.CartEntity;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.infrastructure.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.stream.Collectors;

@Entity @Getter
@Table(name = "cart_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemEntity {

    @Id @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seq")
    private ItemEntity itemEntity;

    @Column(name = "count", nullable = false)
    private Integer count;
    @Column(name = "price", nullable = false)
    private Integer price;

    @Builder
    public CartItemEntity(CartEntity cartEntity, ItemEntity itemEntity, Integer count, Integer price) {
        this.cartEntity = cartEntity;
        this.itemEntity = itemEntity;
        this.count = count;
        this.price = price;
    }

    public static CartItemEntity from(CartItem cartItem){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.id = cartItem.getId();
        cartItemEntity.cartEntity = CartEntity.from(cartItem.getCart());
        cartItemEntity.itemEntity = ItemEntity.from(cartItem.getItem());
        cartItemEntity.count = cartItem.getCount();
        cartItemEntity.price = cartItem.getPrice();
        return cartItemEntity;

    }

    public static CartItemEntity createCartItem(CartEntity cartEntity, ItemEntity itemEntity, Integer price, Integer count){
        return new CartItemEntity(cartEntity, itemEntity, count, price);
    }

    public CartItem toModel(){
        return CartItem.builder()
                .id(id)
                .cart(cartEntity.toModel())
                .count(count)
                .item(itemEntity.toModel())
                .price(price)
                .build();
    }
    public void addCount(Integer count){
        this.count += count;
    }


}
