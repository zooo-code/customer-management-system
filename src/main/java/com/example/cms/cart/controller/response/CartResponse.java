package com.example.cms.cart.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CartResponse {

    private Integer totalCount;
    private Integer totalPrice;
    private Long cartId;
    private Long memberId;

    @Builder
    public CartResponse(Integer totalCount, Integer totalPrice) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
    }

    public CartResponse(Integer totalCount, Integer totalPrice, Long cartId, Long memberId) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.cartId = cartId;
        this.memberId = memberId;
    }
}
