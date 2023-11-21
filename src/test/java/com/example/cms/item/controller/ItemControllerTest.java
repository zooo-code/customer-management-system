package com.example.cms.item.controller;

import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;
import com.example.cms.mock.TestClockHolder;
import com.example.cms.mock.item.ItemTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        ResponseEntity<ItemResponse> itemResponseEntity = itemTestContainer.itemController.create(test);
        assertThat(itemResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(itemResponseEntity.getBody()).isNotNull();
        assertThat(itemResponseEntity.getBody().getCost()).isEqualTo(100);
        assertThat(itemResponseEntity.getBody().getName()).isEqualTo("test");
        assertThat(itemResponseEntity.getBody().getHotIce()).isEqualTo(EItemStatus.HOT);

    }


    @Test
    @DisplayName("아이템을 수정 할 수 있다.")
    void ItemUpdateController() {
        ItemTestContainer itemTestContainer = ItemTestContainer.builder()
                .clockHolder(new TestClockHolder(1L))
                .build();
        ItemCreate test = ItemCreate.builder()
                .name("test")
                .cost(100)
                .hotIce(EItemStatus.HOT)
                .build();

        ItemUpdate update = ItemUpdate.builder()
                .name("test")
                .hotIce(EItemStatus.ICED)
                .cost(1000)
                .build();
        itemTestContainer.itemController.create(test);
        itemTestContainer.itemController.findByName(test.getName());
        ResponseEntity<ItemResponse> update1 = itemTestContainer.itemController.update(update);

        assertThat(update1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(update1.getBody()).isNotNull();
        assertThat(update1.getBody().getCost()).isEqualTo(update.getCost());
        assertThat(update1.getBody().getHotIce()).isEqualTo(update.getHotIce());

    }

    @Test
    @DisplayName("아이템을 모든 조회할 수 있다.")
    void ItemFindController() {
        ItemTestContainer itemTestContainer = ItemTestContainer.builder()
                .clockHolder(new TestClockHolder(1L))
                .build();
        for (int i = 0 ; i < 10 ; i++){
            ItemCreate test = ItemCreate.builder()
                    .name("test" +i)
                    .cost(100 +i)
                    .hotIce(EItemStatus.HOT)
                    .build();
            itemTestContainer.itemController.create(test);
        }
        ResponseEntity<List<ItemResponse>> all = itemTestContainer.itemController.findAll();
        assertThat(all.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(all.getBody()).isNotNull();
        assertThat(all.getBody().size()).isEqualTo(10);


    }
}