package com.example.cms.cart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepositoryJpa extends JpaRepository<CartEntity,Long>, CartCustomRepository {



}
