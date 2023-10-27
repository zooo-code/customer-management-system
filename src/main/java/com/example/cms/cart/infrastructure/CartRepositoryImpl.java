package com.example.cms.cart.infrastructure;

import com.example.cms.cart.domain.Cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements com.example.cms.cart.service.port.CartRepository {

    private final CartRepositoryJpa cartRepositoryJpa;

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepositoryJpa.findById(id).map(CartEntity::toModel);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepositoryJpa.save(CartEntity.from(cart)).toModel();
    }
}
