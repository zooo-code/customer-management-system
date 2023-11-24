package com.example.cms.item.service.port;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;


import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> findByName(String name);
    List<Item> findAll();
    //Optional<ItemEntity> findAllByNameLike(String name);

    List<Item> findAllByNameContaining(String name);

    boolean existsByNameAndAndHotIce(String name, EItemStatus hotIce);
    //Optional<ItemEntity> findByNameAndHotIce(String name, EItemStatus hotIce);

    Item findByNameAndHotIce(String name, EItemStatus hotIce);

    void deleteByItemId(Long id);

    boolean existsByNameAndCostAndHotIce(String name, int cost, EItemStatus hotIce);

    Item save(Item item);

    List<Item> searchItems(String name, Integer cost, EItemStatus eItemStatus);

    List<Item> popularItems();
}
