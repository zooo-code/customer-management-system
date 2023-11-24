package com.example.cms.item.infrastructure;

import com.example.cms.cartitem.infrastructure.CartItemRepositoryJpa;
import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.service.port.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemRepositoryJpa itemRepositoryJpa;
    private final CartItemRepositoryJpa cartItemRepositoryJpa;

    @Override
    public Optional<Item> findByName(String name) {
        return itemRepositoryJpa.findByName(name).map(ItemEntity::toModel);
    }

    @Override
    public List<Item> findAll() {
        return itemRepositoryJpa.findAll()
                .stream()
                .map(ItemEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findAllByNameContaining(String name) {
        return itemRepositoryJpa.findAllByNameContaining(name)
                .stream()
                .map(ItemEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNameAndAndHotIce(String name, EItemStatus hotIce) {
        return itemRepositoryJpa.existsByNameAndAndHotIce(name,hotIce);
    }

    @Override
    public Item findByNameAndHotIce(String name, EItemStatus hotIce) {
        return itemRepositoryJpa.findByNameAndHotIce(name,hotIce).toModel();
    }

    @Override
    public void deleteByItemId(Long id) {
        itemRepositoryJpa.deleteByItemId(id);
    }

    @Override
    public boolean existsByNameAndCostAndHotIce(String name, int cost, EItemStatus hotIce) {
        return itemRepositoryJpa.existsByNameAndCostAndHotIce(name,cost,hotIce);
    }

    @Override
    public Item save(Item item) {
        return itemRepositoryJpa.save(ItemEntity.from(item)).toModel();
    }

    @Override
    public List<Item> searchItems(String name, Integer cost, EItemStatus eItemStatus) {
        return itemRepositoryJpa
                .searchItems(name,cost,eItemStatus)
                .stream().map(ItemEntity::toModel)
                .collect(Collectors.toList());
    }


}
