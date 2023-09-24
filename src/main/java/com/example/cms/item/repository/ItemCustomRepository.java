package com.example.cms.item.repository;

import com.example.cms.item.domain.Item;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemCustomRepository {
    List<Item> searchItems(Item item);

    PageImpl<Item> searchItemsPaging(Pageable pageable, Item filter);
}
