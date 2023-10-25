package com.example.cms.cart.service;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.service.port.CartItemRepository;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.member.service.MemberServiceImpl;
import com.example.cms.mock.FakeCartItemRepository;
import com.example.cms.mock.FakeCartRepository;
import com.example.cms.mock.FakeItemRepository;
import com.example.cms.mock.FakeMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        fakeCartRepository.save(Cart
                .builder().build());
        fakeCartRepository.save(Cart
                .builder().build());

    }
    @Test
    public void 카트를_만들_수_있다() {
        //given

        //when

        //then
    }

}