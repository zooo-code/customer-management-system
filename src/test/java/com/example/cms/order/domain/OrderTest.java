package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;

import com.example.cms.mock.TestClockHolder;
import com.example.cms.mock.TestUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    void OrderCreate_객체로_주문을_만들수_있다(){

        //given
        Member member = Member.builder()
                .id(1L)
                .name("123")
                .phone("1234")
                .status(EMemberStatus.OPEN)
                .membershipPoint(100000)
                .build();
        //주문을 하기 위해서는 카트가 필요하다 -> 카트는 아이템으로 구성 되어있다.

        Item test = Item.builder()
                .hotIce(EItemStatus.HOT)
                .cost(1000)
                .name("test")
                .build();

        CartItem cartItem = CartItem.builder()
                .item(test)
                .price(test.getCost() * 3)
                .count(3)
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = Cart.builder()
                .id(1L)
                .cartItems(cartItems)
                .count(cartItem.getCount())
                .totalPrice(cartItem.getPrice())
                .build();

        OrderCreate orderCreate = OrderCreate.builder()
                .cartId(1L)
                .mobile(member.getPhone())
                .cartId(cart.getId())
                .payment(EPayments.POINT)
                .build();
        assertThat(orderCreate.getMobile()).isEqualTo("1234");
        assertThat(orderCreate.getCartId()).isEqualTo(1L);
        Order newOrder = Order.from(orderCreate,
                new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                , member
                , cart
                , new TestClockHolder(1L));

        assertThat(newOrder.getMember()).isEqualTo(member);
        assertThat(newOrder.getCreatedAt()).isEqualTo(1L);
        assertThat(newOrder.getOrdersId()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        assertThat(newOrder.getCart()).isEqualTo(cart);
        assertThat(newOrder.getCart().getId()).isEqualTo(cart.getId());
        assertThat(newOrder.getPayment()).isEqualTo(EPayments.POINT);
        assertThat(newOrder.getOrdersPrice()).isEqualTo(3000);
    }
    @Test
    @DisplayName("주문을 취소 할 수 있다.")
    void OrderCancel(){

        //given
        Member member = Member.builder()
                .id(1L)
                .name("123")
                .phone("1234")
                .status(EMemberStatus.OPEN)
                .membershipPoint(100000)
                .build();
        //주문을 하기 위해서는 카트가 필요하다 -> 카트는 아이템으로 구성 되어있다.

        Item test = Item.builder()
                .hotIce(EItemStatus.HOT)
                .cost(1000)
                .name("test")
                .build();

        CartItem cartItem = CartItem.builder()
                .item(test)
                .price(test.getCost() * 3)
                .count(3)
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = Cart.builder()
                .id(1L)
                .cartItems(cartItems)
                .count(cartItem.getCount())
                .totalPrice(cartItem.getPrice())
                .build();

        OrderCreate orderCreate = OrderCreate.builder()
                .cartId(1L)
                .mobile(member.getPhone())
                .cartId(cart.getId())
                .payment(EPayments.POINT)
                .build();

        Order newOrder = Order.from(orderCreate,
                new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                , member
                , cart
                , new TestClockHolder(1L));

        newOrder.cancel(() -> 100L);

        assertThat(newOrder.getStatus()).isEqualTo(EOrderStatus.CANCEL);
        assertThat(newOrder.getCancelDate()).isEqualTo(100L);

    }

    @Test
    @DisplayName("주문을 시작할 수 있다.")
    void OrderStart(){

        //given
        Member member = Member.builder()
                .id(1L)
                .name("123")
                .phone("1234")
                .status(EMemberStatus.OPEN)
                .membershipPoint(100000)
                .build();
        //주문을 하기 위해서는 카트가 필요하다 -> 카트는 아이템으로 구성 되어있다.

        Item test = Item.builder()
                .hotIce(EItemStatus.HOT)
                .cost(1000)
                .name("test")
                .build();

        CartItem cartItem = CartItem.builder()
                .item(test)
                .price(test.getCost() * 3)
                .count(3)
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = Cart.builder()
                .id(1L)
                .cartItems(cartItems)
                .count(cartItem.getCount())
                .totalPrice(cartItem.getPrice())
                .build();

        OrderCreate orderCreate = OrderCreate.builder()
                .cartId(1L)
                .mobile(member.getPhone())
                .cartId(cart.getId())
                .payment(EPayments.POINT)
                .build();

        Order newOrder = Order
                .from(orderCreate, new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"), member,
                        cart, new TestClockHolder(1L));

        Long start = newOrder.start(() -> 100L);

        assertThat(newOrder.getStatus()).isEqualTo(EOrderStatus.START);
        assertThat(start).isEqualTo(100L);

    }

}