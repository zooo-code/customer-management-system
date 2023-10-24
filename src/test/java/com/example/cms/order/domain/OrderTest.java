package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    void OrderCreate_객체로_주문을_만들수_있다(){
        //given
        Member test = Member.builder()
                .name("test")
                .phone("1234")
                .status(EMemberStatus.OPEN)
                .membershipPoint(100000)
                .build();
        Item drink = Item.builder()
                .name("test1")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();

        CartItem cartItem = CartItem.builder()
                .count(3)
                .price(drink.getCost())
                .item(drink)
                .build();
        List<CartItem> arrayList = new ArrayList<>();
        arrayList.add(cartItem);
        Cart cart = Cart.builder()
                .member(test)
                .build();
        cart.addCartItem(cartItem);
        cart.addCountCart(cartItem.getCount());
        cart.addTotalPrice(cartItem.getPrice()*cartItem.getCount());

        //when
        OrderCreate orderCreate = OrderCreate.builder()
                .build();
        Order order = orderCreate.toOrder(test, cart);
        //then
        Assertions.assertThat(order.getOrdersPrice()).isEqualTo(drink.getCost() * 3);

    }


}