package com.example.cms.item.controller.port;


import com.example.cms.item.domain.ItemCreate;
import com.example.cms.item.domain.ItemUpdate;
import com.example.cms.item.domain.Item;


import java.util.List;

public interface ItemService {

    List<Item> findAll();
    List<Item> findByName(String name);

    void create(ItemCreate itemCreate);


    void update(ItemUpdate updateRequest);
    void delete(Long itemId);
}
