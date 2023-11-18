package com.example.cms.order.service;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.domain.ECartStatus;
import com.example.cms.cart.service.port.CartRepository;
import com.example.cms.member.domain.Member;
import com.example.cms.member.service.port.MemberRepository;
import com.example.cms.order.controller.port.OrderService;
import com.example.cms.order.domain.Cashier;
import com.example.cms.order.domain.Order;
import com.example.cms.order.domain.OrderCreate;
import com.example.cms.order.execption.CanNotOrderException;
import com.example.cms.order.service.port.OrderRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import com.example.cms.utils.common.service.port.UuidHolder;
import com.example.cms.utils.exception.CommonException;
import com.example.cms.utils.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static com.example.cms.utils.exception.ErrorCode.DATA_NOT_FOUND;

@Builder
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UuidHolder uuidHolder;
    private final ClockHolder clockHolder;

    @Override
    @Transactional
    public Order createOrder(OrderCreate orderCreate) {

        Member member = memberRepository.findByMobile(orderCreate.getMobile())
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));

        Cart cart = cartRepository.findById(orderCreate.getCartId())
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));
        if (cart.getStatus() != ECartStatus.ORDER){
            throw new CanNotOrderException("OrderStatus is cancel");
        }

        Order order = Order.from(orderCreate, uuidHolder, member, cart, clockHolder);


        Cashier cashier = new Cashier();
        Member calMember = cashier.calculate(member, order);
        memberRepository.save(calMember);
        return orderRepository.save(order);

    }


    @Override
    @Transactional(readOnly = true)
    public List<Order> findByOrdersId(String orderId) {
        Order order = orderRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new IllegalStateException("주문 내역이 없습니다."));

        return List.of(order);
    }
    @Override
    @Transactional
    public void cancel(String orderId) {

        Order order = orderRepository
                .findByOrdersId(orderId)
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));
        order.cancel(clockHolder);

    }

    @Override
    public Long start(String orderId) {
        Order order = orderRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new CommonException(DATA_NOT_FOUND));
        return order.start(clockHolder);
    }
}
