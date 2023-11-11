package com.example.cms.mock;

import com.example.cms.cart.service.port.CartRepository;
import com.example.cms.cartitem.service.port.CartItemRepository;
import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.service.MemberServiceImpl;
import com.example.cms.member.service.port.MemberRepository;
import com.example.cms.order.service.port.OrderRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import com.example.cms.utils.common.service.port.UuidHolder;
import lombok.Builder;

public class TestContainer {

    private MemberRepository memberRepository;
    private ItemRepository itemRepository;
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    private MemberService memberService;

    @Builder
    public TestContainer(ClockHolder clockHolder , UuidHolder uuidHolder) {
        this.memberRepository = new FakeMemberRepository();
        this.itemRepository = new FakeItemRepository();
        this.cartItemRepository = new FakeCartItemRepository();
        this.cartRepository = new FakeCartRepository();
        this.orderRepository = new FakeOrderRepository();
        this.memberService = MemberServiceImpl
                .builder()
                .memberRepository(this.memberRepository)
                .build();
    }
}
