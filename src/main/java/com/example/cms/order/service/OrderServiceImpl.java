package com.example.cms.order.service;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.service.port.CartRepository;
import com.example.cms.member.domain.Member;
import com.example.cms.member.service.port.MemberRepository;
import com.example.cms.order.controller.port.OrderService;
import com.example.cms.order.domain.Order;
import com.example.cms.order.domain.OrderCreate;
import com.example.cms.order.service.port.OrderRepository;
import com.example.cms.utils.exception.CommonException;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static com.example.cms.utils.exception.ErrorCode.DATA_NOT_FOUND;

@Builder
@Service
public class OrderServiceImpl implements OrderService {


    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(MemberRepository memberRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    @Transactional
    public Order createOrder(OrderCreate orderCreate) {

        //1. 회원 멤버십 포인트 확인
        Member member = memberRepository.findByMobile(orderCreate.getMobile())
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));

        Cart cart = cartRepository.findById(orderCreate.getCartId())
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));

        Order order = orderCreate.toOrder(member, cart);
        //1-2. payment 확인
        //2. 포인트 차감
        calculateMemberPoint(member, order);

        return orderRepository.save(order);

    }

    private void calculateMemberPoint(Member member, Order order) {
        int memberPoint = order.getMember().getMembershipPoint();
        int payAmount = order.getOrdersPrice();
        if (memberPoint < payAmount) {
            throw new IllegalStateException("포인트가 부족합니다.");
        }
        int remainPoint = memberPoint - payAmount;
        //남은 포인트 set
        member.updatePoint(remainPoint);
        memberRepository.save(member);
    }

    @Override
    public List<Order> findByOrdersId(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("주문 내역이 없습니다."));

        return List.of(order);
    }
    @Override
    public void cancel(String orderId) {
        //1.포인트 업뎃


//        member.updatePoint(resultPoint);

        //2. 오더 삭제
//        orderRepository.deleteById(orderId);
    }
}
