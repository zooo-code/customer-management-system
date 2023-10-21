package com.example.cms.item.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemUpdate {

    private Long itemId;
    private String name;
    private Integer cost;
    private EItemStatus hotIce;

    @Builder
    public ItemUpdate(@JsonProperty("itemId") Long itemId,
                      @JsonProperty("name") String name,
                      @JsonProperty("cost") Integer cost,
                      @JsonProperty("status") EItemStatus hotIce) {
        this.itemId = itemId;
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }


    public Item toItem(){
        return Item.builder()
                .itemId(itemId)
                .name(name)
                .cost(cost)
                .hotIce(hotIce)
                .build();
    }
}
