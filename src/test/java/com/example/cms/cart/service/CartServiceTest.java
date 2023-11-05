package com.example.cms.cart.service;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.cartitem.service.port.CartItemRepository;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.member.service.MemberServiceImpl;
import com.example.cms.mock.*;
import com.example.cms.order.domain.OrderCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CartServiceTest {
    private CartServiceImpl cartService;


    @BeforeEach
    void init(){
        FakeCartRepository fakeCartRepository = new FakeCartRepository();
        FakeItemRepository fakeItemRepository = new FakeItemRepository();
        FakeCartItemRepository fakeCartItemRepository = new FakeCartItemRepository();
        this.cartService = CartServiceImpl.builder()
                .cartRepository(fakeCartRepository)
                .cartItemRepository(fakeCartItemRepository)
                .itemRepository(fakeItemRepository)
                .build();
        Item coffee = Item.builder()
                .itemId(1L)
                .name("coffee")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();
        Item ade = Item.builder()
                .itemId(2L)
                .name("ade")
                .cost(2000)
                .hotIce(EItemStatus.ICED)
                .build();
        fakeItemRepository.save(coffee);
        fakeItemRepository.save(ade);
        Cart cart = Cart.cartCreate(new TestClockHolder(1L));
        CartItem cartItemCoffee = CartItem.builder()
                .id(1L)
                .item(coffee)
                .cart(cart)
                .count(3)
                .price(coffee.getCost())
                .build();
        CartItem cartItemAde = CartItem.builder()
                .id(2L)
                .item(ade)
                .cart(cart)
                .count(3)
                .price(ade.getCost())
                .build();

        cart.addCartItem(cartItemAde);
        cart.addCountCart(cartItemAde.getCount());
        cart.addTotalPrice(cartItemAde.getPrice()*cartItemAde.getCount());

        cart.addCartItem(cartItemCoffee);
        cart.addTotalPrice(cartItemCoffee.getPrice()*cartItemCoffee.getCount());
        cart.addCountCart(cartItemCoffee.getCount());

        fakeCartRepository.save(cart);



    }
    @Test
    public void 카트를_만들_수_있다() {
        //given

        //when
        //then
    }

}