package com.example.cms.cartitem.infrastructure;

import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.cartitem.service.port.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemRepositoryJpa cartItemRepositoryJpa;

    @Override
    public void save(CartItem cartItem) {
        cartItemRepositoryJpa.save(CartItemEntity.from(cartItem));
    }
}
