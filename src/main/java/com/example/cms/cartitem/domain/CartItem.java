package com.example.cms.cartitem.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter
@Table(name = "cart_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seq")
    private Item item;

    private Integer count;

    private Integer price;

    @Builder
    public CartItem(Cart cart, Item item, Integer count, Integer price) {
        this.cart = cart;
        this.item = item;
        this.count = count;
        this.price = price;
    }


    public static CartItem createCartItem(Cart cart, Item item, Integer price, Integer count){
        return new CartItem(cart, item, count, price);
    }


    public void addCount(Integer count){
        this.count += count;
    }


}
