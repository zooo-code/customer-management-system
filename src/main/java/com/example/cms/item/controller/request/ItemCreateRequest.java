package com.example.cms.item.controller.request;

import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemCreateRequest {

    private String name;
    private Integer cost;
    private ItemStatus hotIce;

    @Builder
    public ItemCreateRequest(String name, Integer cost, ItemStatus hotIce) {
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }


    public Item toItem(){
        return Item.builder()
                .name(name)
                .cost(cost)
                .hotIce(hotIce)
                .build();
    }
}
