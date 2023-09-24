package com.example.cms.cartitem.controller.response;


import com.example.cms.item.domain.ItemStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class CartItemCreateResponse {

    private String name;
    private Integer price;
    private ItemStatus status;
    private Integer count;

    @Builder
    public CartItemCreateResponse(String name, Integer price, ItemStatus status, Integer count) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.count = count;
    }
}
