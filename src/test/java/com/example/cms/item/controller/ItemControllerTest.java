package com.example.cms.item.controller;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.mock.TestClockHolder;
import com.example.cms.mock.item.ItemTestContainer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemControllerTest {

    @Test
    @DisplayName("아이템을 생산 할 수 있다.")
    void ItemCreateController() {
        ItemTestContainer itemTestContainer = ItemTestContainer.builder()
                .clockHolder(new TestClockHolder(1L))
                .build();
        ItemCreate test = ItemCreate.builder()
                .name("test")
                .cost(100)
                .hotIce(EItemStatus.HOT)
                .build();
        ResponseEntity<Item> itemResponseEntity = itemTestContainer.itemController.create(test);
        assertThat(itemResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(itemResponseEntity.getBody()).isNotNull();
        assertThat(itemResponseEntity.getBody().getCost()).isEqualTo(100);
        assertThat(itemResponseEntity.getBody().getName()).isEqualTo("test");
        assertThat(itemResponseEntity.getBody().getHotIce()).isEqualTo(EItemStatus.HOT);
        assertThat(itemResponseEntity.getBody().getCreateAt()).isEqualTo(1L);

    }


    @Test
    @DisplayName("아이템을 생산 할 수 있다.")
    void ItemUpdateController() {
        ItemTestContainer itemTestContainer = ItemTestContainer.builder()
                .clockHolder(new TestClockHolder(1L))
                .build();
        ItemCreate test = ItemCreate.builder()
                .name("test")
                .cost(100)
                .hotIce(EItemStatus.HOT)
                .build();
        ResponseEntity<Item> itemResponseEntity = itemTestContainer.itemController.create(test);
        assertThat(itemResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(itemResponseEntity.getBody()).isNotNull();
        assertThat(itemResponseEntity.getBody().getCost()).isEqualTo(100);
        assertThat(itemResponseEntity.getBody().getName()).isEqualTo("test");
        assertThat(itemResponseEntity.getBody().getHotIce()).isEqualTo(EItemStatus.HOT);
        assertThat(itemResponseEntity.getBody().getCreateAt()).isEqualTo(1L);

    }
}