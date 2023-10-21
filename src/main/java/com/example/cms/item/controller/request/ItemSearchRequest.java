package com.example.cms.item.controller.request;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.infrastructure.ItemEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemSearchRequest {

    private String name;
    private Integer cost;
    private EItemStatus hotIce;

    @Builder
    public ItemSearchRequest(String name, Integer cost, EItemStatus hotIce) {
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }


    public ItemEntity toItem(){
        return ItemEntity.builder()
                .name(name)
                .cost(cost)
                .hotIce(hotIce)
                .build();
    }
}
