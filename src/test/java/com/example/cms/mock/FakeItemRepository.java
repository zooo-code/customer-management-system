package com.example.cms.mock;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.Item;
import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.utils.exception.CommonException;
import com.example.cms.utils.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FakeItemRepository implements ItemRepository {

    private final AtomicLong autoGeneratedId =new AtomicLong(0);
    private final List<Item> data = new ArrayList<>();

    @Override
    public Optional<Item> findByName(String name) {
        return data.stream().filter(test ->test.getName().equals(name)).findFirst();
    }

    @Override
    public List<Item> findAll() {
        return data;
    }

    @Override
    public List<Item> findAllByNameContaining(String name) {
        return data.stream()
                .filter(test -> test.getName().toString().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNameAndAndHotIce(String name, EItemStatus hotIce) {
        boolean empty = data.stream()
                .filter(test -> test.getName().equals(name) && test.getHotIce().equals(hotIce))
                .collect(Collectors.toList()).isEmpty();
        return !empty;
    }

    @Override
    public Item findByNameAndHotIce(String name, EItemStatus hotIce) {
        return data.stream()
                .filter(test -> test.getName().equals(name) && test.getHotIce().equals(hotIce))
                .findAny().get();
    }

    @Override
    public void deleteByItemId(Long id) {

        Item first = data.stream()
                .filter(test -> test.getItemId().equals(id))
                .findFirst().orElseThrow(()-> new CommonException(ErrorCode.DATA_NOT_FOUND));
        data.remove(first);
    }

    @Override
    public boolean existsByNameAndCostAndHotIce(String name, int cost, EItemStatus hotIce) {
        return !data.stream()
                .filter(test ->
                        test.getName().equals(name) && test.getHotIce().equals(hotIce)&&test.getCost().equals(cost))
                .collect(Collectors.toList())
                .isEmpty();
    }

    @Override
    public Item save(Item item) {
        if (item.getItemId() == null || item.getItemId() == 0){
            Item saveItem = Item.builder()
                    .itemId(autoGeneratedId.incrementAndGet())
                    .name(item.getName())
                    .cost(item.getCost())
                    .hotIce(item.getHotIce())
                    .build();
            data.add(saveItem);
            return saveItem;
        } else {
            data.removeIf(test -> Objects.equals(test.getItemId(),item.getItemId()));
            data.add(item);
            return item;
        }

    }

    @Override
    public List<Item> searchItems(String name, Integer cost, EItemStatus eItemStatus) {
        return data.stream()
                .filter(test -> test.getName().contains(name)
                        ||test.getCost().equals(cost)
                        ||test.getHotIce().equals(eItemStatus))
                .collect(Collectors.toList());
    }
}
