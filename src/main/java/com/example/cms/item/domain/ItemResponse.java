package com.example.cms.item.domain;

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

    public static ItemResponse of(ItemEntity itemEntity){
        return ItemResponse.builder()
                .name(itemEntity.getName())
                .cost(itemEntity.getCost())
                .hotIce(itemEntity.getHotIce())
                .build();
    }
}
