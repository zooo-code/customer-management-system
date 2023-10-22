package com.example.cms.order.infrastructure;

import com.example.cms.cart.infrastructure.CartEntity;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.order.domain.EPayments;
import com.example.cms.order.domain.Order;
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
public class OrderEntity extends BaseDateTimeEntity {

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
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Builder
    public OrderEntity(Long id, String ordersId, LocalDateTime cancelDate, Integer ordersPrice, EPayments payment, CartEntity cartEntity, MemberEntity memberEntity) {
        this.id = id;
        this.ordersId = ordersId;
        this.cancelDate = cancelDate;
        this.ordersPrice = ordersPrice;
        this.payment = payment;
        this.cartEntity = cartEntity;
        this.memberEntity = memberEntity;
    }

    public void setOrdersId(String ordersId){
        this.ordersId = ordersId;
    }

    public void cancel(LocalDateTime cancelDate){
        this.cancelDate = cancelDate;
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
                .build();

    }
}
