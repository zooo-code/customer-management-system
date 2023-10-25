package com.example.cms.cartitem.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    public void  Item_객체로_cartItem_을_만들_수_있다() {
        //given
        Item test = Item.builder()
                .name("test")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .build();
        //when
        CartItem cartItem = CartItem.createCartItem(cart, test, 3);

        //then
        assertThat(cartItem.getCart().getId()).isEqualTo(cart.getId());
        assertThat(cartItem.getItem().getCost()).isEqualTo(test.getCost());

    }

}