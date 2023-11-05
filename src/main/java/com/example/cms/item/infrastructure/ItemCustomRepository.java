package com.example.cms.item.infrastructure;

import com.example.cms.item.domain.EItemStatus;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemCustomRepository {
    List<ItemEntity> searchItems(String name, Integer cost, EItemStatus eItemStatus);
//
//    PageImpl<ItemEntity> searchItemsPaging(Pageable pageable, ItemEntity filter);
}
