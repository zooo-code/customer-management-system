package com.example.cms.item.controller.response;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.infrastructure.ItemEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemResponse {
    private String name;
    private Integer cost;
    private EItemStatus hotIce;


    @Builder
    public ItemResponse(String name, Integer cost, EItemStatus hotIce) {
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }

    public static ItemResponse from(Item item){
        return ItemResponse.builder()
                .name(item.getName())
                .cost(item.getCost())
                .hotIce(item.getHotIce())
                .build();
    }
}
