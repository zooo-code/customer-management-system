package com.example.cms.item.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemUpdate {

    private final String name;
    private final Integer cost;
    private final EItemStatus hotIce;
    @Builder
    public ItemUpdate(@JsonProperty("name") String name,
                      @JsonProperty("cost") Integer cost,
                      @JsonProperty("status") EItemStatus hotIce) {
        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }
}
