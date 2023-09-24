package com.example.cms.cart.repository;

import com.example.cms.cart.domain.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {



}
