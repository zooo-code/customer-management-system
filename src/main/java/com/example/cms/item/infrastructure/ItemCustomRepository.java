package com.example.cms.item.infrastructure;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemCustomRepository {
    List<ItemEntity> searchItems(ItemEntity itemEntity);

    PageImpl<ItemEntity> searchItemsPaging(Pageable pageable, ItemEntity filter);
}
