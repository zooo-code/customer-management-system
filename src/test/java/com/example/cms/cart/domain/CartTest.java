package com.example.cms.cart.domain;

import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CartTest {

    @Test
    void Member_객체로_Cart_를_생성할_수_있다(){
        //given
        Member test = Member.builder()
                .name("test")
                .phone("1234")
                .membershipPoint(10000)
                .status(EMemberStatus.OPEN)
                .build();

        //when
        Cart cart = Cart.from(test);

        //then
        assertThat(cart.getMember()).isEqualTo(test);
        assertThat(cart.getCartItem()).size().isEqualTo(0);
        assertThat(cart.getCount()).isEqualTo(0);
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }

}