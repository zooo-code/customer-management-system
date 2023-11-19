package com.example.cms.item.domain;


import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Item {

    private final Long itemId;
    private final String name;
    private final Integer cost;
    private final EItemStatus hotIce;
    private final Long createAt;
    @Builder
    public Item(Long itemId, String name, Integer cost, EItemStatus hotIce, Long createAt) {
        this.itemId = itemId;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
        this.createAt = createAt;
    }




    public static Item from(ItemCreate itemCreate, ClockHolder clockHolder){
        return Item.builder()
                .name(itemCreate.getName())
                .hotIce(itemCreate.getHotIce())
                .cost(itemCreate.getCost())
                .createAt(clockHolder.millis())
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
