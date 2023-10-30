package com.example.cms.order.controller.response;

import com.example.cms.order.domain.EPayments;
import com.example.cms.order.domain.Order;
import com.example.cms.order.infrastructure.OrderEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class OrderDetailResponse {

    private String ordersId;
    private String memberName;
    private String mobile;
    private Long createdAt;
    private Long cancelDate;
    private EPayments payments;

    @Builder
    public OrderDetailResponse(String ordersId, String memberName, String mobile, Long createdAt, Long cancelDate, EPayments payments) {
        this.ordersId = ordersId;
        this.memberName = memberName;
        this.mobile = mobile;
        this.createdAt = createdAt;
        this.cancelDate = cancelDate;
        this.payments = payments;
    }

    public static OrderDetailResponse from(Order order){
        return OrderDetailResponse.builder()
                .ordersId(order.getOrdersId())
                .memberName(order.getMember().getName())
                .mobile(order.getMember().getPhone())
                .createdAt(order.getCreatedAt())
                .cancelDate(order.getCancelDate())
                .payments(order.getPayment())
                .build();
    }

}
