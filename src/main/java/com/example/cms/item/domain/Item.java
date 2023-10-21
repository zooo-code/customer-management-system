package com.example.cms.item.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

    private final Long itemId;
    private final String name;
    private final Integer cost;
    private final EItemStatus hotIce;

    @Builder
    public Item(Long itemId, String name, Integer cost, EItemStatus hotIce) {
        this.itemId = itemId;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }

    public static Item from(ItemCreate itemCreate){
        return Item.builder()
                .name(itemCreate.getName())
                .hotIce(itemCreate.getHotIce())
                .cost(itemCreate.getCost())
                .build();
    }

    public Item update(ItemUpdate itemUpdate){
        return Item.builder()
                .itemId(itemId)
                .name(itemUpdate.getName())
                .hotIce(itemUpdate.getHotIce())
                .cost(itemUpdate.getCost())
                .build();
    }

}
