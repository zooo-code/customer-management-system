package com.example.cms.order.infrastructure;

import com.example.cms.cart.infrastructure.CartEntity;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.order.domain.EOrderStatus;
import com.example.cms.order.domain.EPayments;
import com.example.cms.order.domain.Order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity   {

    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    @Column(name = "orders_id", nullable = false)
    private String ordersId;

    @Column(name = "cancel_date")
    private Long cancelDate;

    @Column(name = "orders_price", nullable = false)
    private Integer ordersPrice;

    @Column(name = "payment", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPayments payment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column(name = "create_at")
    private Long createAt;

    @Enumerated(EnumType.STRING)
    @Column(name ="order_status", nullable = false)
    private EOrderStatus status;
    @Builder
    public OrderEntity(Long id, String ordersId, Long cancelDate, Integer ordersPrice, EPayments payment, CartEntity cartEntity, MemberEntity memberEntity, Long createAt, EOrderStatus status) {
        this.id = id;
        this.ordersId = ordersId;
        this.cancelDate = cancelDate;
        this.ordersPrice = ordersPrice;
        this.payment = payment;
        this.cartEntity = cartEntity;
        this.memberEntity = memberEntity;
        this.createAt = createAt;
        this.status = status;
    }



    public static OrderEntity from(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.id = order.getId();
        orderEntity.ordersId = order.getOrdersId();
        orderEntity.cancelDate = order.getCancelDate();
        orderEntity.ordersPrice = order.getOrdersPrice();
        orderEntity.payment = order.getPayment();
        orderEntity.cartEntity = CartEntity.from(order.getCart());
        orderEntity.memberEntity = MemberEntity.from(order.getMember());
        orderEntity.createAt = order.getCreatedAt();
        orderEntity.status = order.getStatus();
        return orderEntity;
    }


    public Order toModel(){
        return Order.builder()
                .id(id)
                .ordersId(ordersId)
                .cart(cartEntity.toModel())
                .ordersPrice(ordersPrice)
                .cancelDate(cancelDate)
                .member(memberEntity.toModel())
                .payment(payment)
                .createdAt(createAt)
                .status(status)
                .build();

    }
}
