package com.example.cms.cart.service;
import com.example.cms.cart.controller.port.CartService;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.domain.Cart;

import com.example.cms.cart.domain.Carter;
import com.example.cms.cart.exception.CartNotFoundException;
import com.example.cms.cart.service.port.CartRepository;
import com.example.cms.cartitem.controller.request.CartItemRequest;
import com.example.cms.cartitem.domain.CartItem;

import com.example.cms.cartitem.service.port.CartItemRepository;
import com.example.cms.item.domain.Item;
import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Builder
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;
    private final ClockHolder clockHolder;

    @Override
    @Transactional
    public Cart CreateCart(CartRequest cartRequest){

        List<CartItemRequest> cartItemRequests = cartRequest.getCartItemRequests();
        Cart cart = Cart.cartCreate(clockHolder);
        Cart saveCart = cartRepository.save(cart);
        Carter carter = new Carter();

        for (CartItemRequest cartItemRequest : cartItemRequests) {
            Item drink = itemRepository
                    .findByNameAndHotIce(cartItemRequest.getName(), cartItemRequest.getStatus());
            Cart makeCart = carter.make(saveCart, drink, cartItemRequest.getCount());
            CartItem cartItem = CartItem
                    .createCartItem(saveCart, drink, cartItemRequest.getCount());
            makeCart.addCartItem(cartItem);
            cartItemRepository.save(cartItem);
        }
        return cartRepository.save(cart);
    }


    @Override
    @Transactional
    public void deleteCart(Long cartId){
        Optional<Cart> findCart = cartRepository.findById(cartId);
        if (findCart.isEmpty()){
            throw new CartNotFoundException("존재하지 않는 장바구니 입니다.");
        }
        Cart cart = findCart.get();
        cart.cartDel();

    }
}
