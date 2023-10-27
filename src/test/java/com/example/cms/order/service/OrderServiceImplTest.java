package com.example.cms.order.service;

import com.example.cms.mock.FakeCartItemRepository;
import com.example.cms.mock.FakeCartRepository;
import com.example.cms.mock.FakeMemberRepository;
import com.example.cms.mock.FakeOrderRepository;
import com.example.cms.order.domain.OrderCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private OrderServiceImpl orderService;
    @BeforeEach
    void init(){
        FakeOrderRepository fakeOrderRepository = new FakeOrderRepository();
        FakeCartRepository fakeCartRepository = new FakeCartRepository();
        FakeMemberRepository fakeMemberRepository = new FakeMemberRepository();
        this.orderService = OrderServiceImpl.builder()
                .memberRepository(fakeMemberRepository)
                .orderRepository(fakeOrderRepository)
                .cartRepository(fakeCartRepository)
                .build();


    }

    @Test
    public void OrderCreate() {
        //given
//        OrderCreate.builder()
//                .cartId()
//                .mobile()
//                .payment()
//                .build();


        //when

        //then
    }

}