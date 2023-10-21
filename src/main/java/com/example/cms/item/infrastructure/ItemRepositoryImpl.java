package com.example.cms.item.infrastructure;

import com.example.cms.item.service.port.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemRepositoryJpa itemRepositoryJpa;
}
