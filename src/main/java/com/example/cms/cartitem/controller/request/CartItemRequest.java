package com.example.cms.cartitem.controller.request;

import com.example.cms.item.domain.EItemStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CartItemRequest {


    private String name;
    @Min(value = 1)
    @NotNull
    private Integer count;

    private EItemStatus status;


    public CartItemRequest() {
    }
    @Builder
    public CartItemRequest(String name, Integer count, EItemStatus status) {
        this.name = name;
        this.count = count;
        this.status = status;
    }
}
