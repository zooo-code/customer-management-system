package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    void OrderCreate_객체로_주문을_만들수_있다(){
        //given
        Member build = Member.builder()
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



    }


}