package com.example.cms.mock.item;

import com.example.cms.item.controller.ItemController;
import com.example.cms.item.controller.port.ItemService;
import com.example.cms.item.service.ItemServiceImpl;
import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;

public class ItemTestContainer {

    public final ItemRepository itemRepository;
    public final ItemService itemService;
    public final ItemController itemController;

    @Builder
    public ItemTestContainer(ClockHolder clockHolder) {
        this.itemRepository = new FakeItemRepository();
        this.itemService = ItemServiceImpl.builder()
                .itemRepository(this.itemRepository)
                .clockHolder(clockHolder)
                .build();
        this.itemController = ItemController.builder()
                .itemService(this.itemService)
                .build();
    }
}
