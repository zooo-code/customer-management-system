package com.example.cms.item.controller.port;


import com.example.cms.item.controller.request.ItemSearchRequest;
import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;
import com.example.cms.item.domain.Item;


import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item findByName(String name);
    List<Item> findAll();
    List<Item> findAllByNameContaining(String name);

    Item create(ItemCreate itemCreate);


    Item update(ItemUpdate updateRequest);
    void delete(Long itemId);
    List<Item> searchItems(ItemSearchRequest itemSearchRequest);
}
