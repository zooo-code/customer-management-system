package com.example.cms.item.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class ItemUpdate {

    private String name;
    private Integer cost;
    private EItemStatus hotIce;

    @Builder
    public ItemUpdate(@JsonProperty("name") String name,
                      @JsonProperty("cost") Integer cost,
                      @JsonProperty("status") EItemStatus hotIce) {

        this.name = name;
        this.cost = cost;
        this.hotIce = hotIce;
    }



}
