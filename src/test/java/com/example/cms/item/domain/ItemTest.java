package com.example.cms.item.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class ItemTest {


    @Test
    void ItemCreate_객체로_아이템을_생성할_수_있다(){
        ItemCreate itemCreate = ItemCreate
                .builder()
                .name("test")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();

        Item item = Item.from(itemCreate);

        assertThat(item.getItemId()).isNull();
        assertThat(item.getName()).isEqualTo(itemCreate.getName());
        assertThat(item.getCost()).isEqualTo(itemCreate.getCost());
        assertThat(item.getHotIce()).isEqualTo(itemCreate.getHotIce());
    }

    @Test
    void ItemUpdate_객체로_아이템을_수정할_수_있다(){
        ItemUpdate itemUpdate = ItemUpdate
                .builder()
                .name("test")
                .cost(1000)
                .hotIce(EItemStatus.HOT)
                .build();

        Item item = Item.builder()
                .itemId(1L)
                .name("test1")
                .cost(1234)
                .hotIce(EItemStatus.ICED)
                .build();
        Item update = item.update(itemUpdate);

        assertThat(update.getItemId()).isEqualTo(1L);
        assertThat(update.getName()).isEqualTo(itemUpdate.getName());
        assertThat(update.getCost()).isEqualTo(itemUpdate.getCost());
        assertThat(update.getHotIce()).isEqualTo(itemUpdate.getHotIce());
    }

}