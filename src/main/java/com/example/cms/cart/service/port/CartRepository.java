package com.example.cms.cart.service.port;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.infrastructure.CartEntity;
import com.example.cms.member.domain.Member;

import java.util.Optional;

public interface CartRepository {

    Optional<CartEntity> findById(Long id);

    Cart save(Cart cart);
}
