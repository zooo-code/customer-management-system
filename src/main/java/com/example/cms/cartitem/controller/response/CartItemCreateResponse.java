package com.example.cms.cartitem.controller.response;


import com.example.cms.item.domain.EItemStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemCreateResponse {

    private String name;
    private Integer price;
    private EItemStatus status;
    private Integer count;

    @Builder
    public CartItemCreateResponse(String name, Integer price, EItemStatus status, Integer count) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.count = count;
    }
}
