package com.example.cms.item.repository;

import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemCustomRepository {
    List<Item> findAll();
    //Optional<Item> findAllByNameLike(String name);

    List<Item> findAllByNameContaining(String name);

    boolean existsByNameAndAndHotIce(String name, ItemStatus hotIce);
    //Optional<Item> findByNameAndHotIce(String name, ItemStatus hotIce);

    Item findByNameAndHotIce(String name, ItemStatus hotIce);

    void deleteByItemId(Long id);

    boolean existsByNameAndCostAndHotIce(String name, int cost, ItemStatus hotIce);
}
