package com.example.cms.cartitem.controller.request;

import com.example.cms.item.domain.ItemStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartItemDeleteRequest {

    private String name;
    @Min(value = 1)
    @NotNull
    private Integer count;
    private ItemStatus status;

    public CartItemDeleteRequest(String name, Integer count, ItemStatus status) {
        this.name = name;
        this.count = count;
        this.status = status;
    }
}
