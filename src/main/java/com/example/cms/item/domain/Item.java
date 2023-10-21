package com.example.cms.item.domain;


import lombok.Getter;

@Getter
public class Item {

    private Long itemId;
    private String name;
    private Integer cost;
    private EItemStatus hotIce;
}
