package com.example.cms.cart.infrastructure;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.service.port.CartRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartRepositoryJpa cartRepositoryJpa;

    @Override
    public Optional<CartEntity> findById(Long id) {
        return cartRepositoryJpa.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        cartRepositoryJpa.save(CartEntity.from(cart));
        return null;
    }
}
