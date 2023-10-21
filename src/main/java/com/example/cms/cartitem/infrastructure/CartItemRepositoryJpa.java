package com.example.cms.cartitem.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepositoryJpa extends JpaRepository<CartItemEntity,Long> {


}
