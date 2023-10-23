package com.example.cms.cart.domain;


import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CartTest {

    @Test
    void Cart_를_생성할_수_있다(){
        //given
        Cart cart = Cart.cartCreate();

        //when
        //then
        assertThat(cart.getCartItem()).size().isEqualTo(0);
        assertThat(cart.getCount()).isEqualTo(0);
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }


    @Test
    void Cart_에_상품의_수를_늘릴_수_있다(){
        //given
        Cart cart = Cart.cartCreate();
        Item test = Item.builder()
                .name("test")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();
        //when
        CartItem cartItem = CartItem.createCartItem(cart, test, 3);
        cart.addCountCart(cartItem.getCount());
        cart.addTotalPrice(cartItem.getPrice()*cartItem.getCount());
        cart.addCartItem(cartItem);

        //then
        assertThat(cart.getCartItem()).size().isEqualTo(1);
        assertThat(cart.getCount()).isEqualTo(3);
        assertThat(cart.getTotalPrice()).isEqualTo(3000);
    }

}