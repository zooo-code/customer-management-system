package com.example.cms.item.infrastructure;

import com.example.cms.item.domain.EItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ItemRepositoryJpa extends JpaRepository<ItemEntity, Long>, ItemCustomRepository {

    Optional<ItemEntity> findByName(String name);
    List<ItemEntity> findAll();
    //Optional<ItemEntity> findAllByNameLike(String name);

    List<ItemEntity> findAllByNameContaining(String name);

    boolean existsByNameAndAndHotIce(String name, EItemStatus hotIce);
    //Optional<ItemEntity> findByNameAndHotIce(String name, EItemStatus hotIce);

    ItemEntity findByNameAndHotIce(String name, EItemStatus hotIce);

    void deleteByItemId(Long id);

    boolean existsByNameAndCostAndHotIce(String name, int cost, EItemStatus hotIce);
}
