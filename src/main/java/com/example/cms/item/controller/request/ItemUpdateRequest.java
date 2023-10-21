package com.example.cms.item.controller.request;

import com.example.cms.item.infrastructure.ItemEntity;
import com.example.cms.item.domain.EItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemUpdateRequest {

    private Long itemId;
    private String name;
    private Integer cost;
    private EItemStatus hotIce;

    @Builder
    public ItemUpdateRequest(Long itemId, String name, Integer cost, EItemStatus hotIce) {
        this.itemId = itemId;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }


    public ItemEntity toItem(){
        return ItemEntity.builder()
                .id(itemId)
                .name(name)
                .cost(cost)
                .hotIce(hotIce)
                .build();
    }
}
