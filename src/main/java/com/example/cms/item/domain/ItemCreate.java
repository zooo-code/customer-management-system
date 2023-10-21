package com.example.cms.item.domain;

import com.example.cms.item.infrastructure.ItemEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemCreate {

    private String name;
    private Integer cost;
    private EItemStatus hotIce;

    @Builder
    public ItemCreate(@JsonProperty("name") String name,
                      @JsonProperty("cost") Integer cost,
                      @JsonProperty("status") EItemStatus hotIce) {
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
