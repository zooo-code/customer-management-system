package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.domain.Member;
import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseDateTimeEntity {

    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    @Column(name = "orders_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ordersId;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "orders_price", nullable = false)
    private Integer ordersPrice;

    @Column(name = "payment", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPayments payment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(Long id, String ordersId, LocalDateTime cancelDate, Integer ordersPrice, EPayments payment, Cart cart, Member member) {
        this.id = id;
        this.ordersId = ordersId;
        this.cancelDate = cancelDate;
        this.ordersPrice = ordersPrice;
        this.payment = payment;
        this.cart = cart;
        this.member = member;
    }

    public void setOrdersId(String ordersId){
        this.ordersId = ordersId;
    }

    public void cancel(LocalDateTime cancelDate){
        this.cancelDate = cancelDate;
    }
}
