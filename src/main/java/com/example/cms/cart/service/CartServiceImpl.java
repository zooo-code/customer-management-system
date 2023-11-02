package com.example.cms.cart.service;
import com.example.cms.cart.controller.port.CartService;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.domain.Cart;

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
    /**
     * 메뉴를 선택한 후에 메뉴들(리스트로 들어옴)이 카드에 담긴다.
     */
    @Override
    @Transactional
    public Cart CreateCart(CartRequest cartRequest){
        //카트 생성
        List<CartItemRequest> cartItemRequests = cartRequest.getCartItemRequests();
        Cart cart = Cart.cartCreate(clockHolder);

        return makeCart(cart,cartItemRequests);
    }

    private Cart makeCart(Cart cart, List<CartItemRequest> cartItemRequests) {
        Cart save = cartRepository.save(cart);
        for (CartItemRequest cartItemRequest : cartItemRequests) {
            //카트에 총 상품의 수를 증가
            Item drink = itemRepository
                    .findByNameAndHotIce(cartItemRequest.getName(), cartItemRequest.getStatus());

            save.addTotalPrice(drink.getCost() * cartItemRequest.getCount());
            save.addCountCart(cartItemRequest.getCount());

            CartItem cartItem = CartItem
                    .createCartItem(save, drink, cartItemRequest.getCount());

            save.addCartItem(cartItem);
            cartItemRepository.save(cartItem);
        }
        return cartRepository.save(save);
    }

//    카트의 상태만 NOORDER 처리
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
