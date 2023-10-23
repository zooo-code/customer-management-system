package com.example.cms.cart.service;
import com.example.cms.cart.controller.port.CartService;
import com.example.cms.cart.controller.request.CartDeleteRequest;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.domain.Cart;

import com.example.cms.cart.exception.CartNotFoundException;
import com.example.cms.cart.service.port.CartRepository;
import com.example.cms.cartitem.controller.request.CartItemRequest;
import com.example.cms.cartitem.domain.CartItem;

import com.example.cms.cartitem.service.port.CartItemRepository;
import com.example.cms.item.domain.Item;
import com.example.cms.item.service.port.ItemRepository;
import com.example.cms.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    /**
     * 메뉴를 선택한 후에 메뉴들(리스트로 들어옴)이 카드에 담긴다.
     */
    @Override
    @Transactional
    public Cart CreateCart(CartRequest cartRequest){
        //카트 생성
        List<CartItemRequest> cartItemRequests = cartRequest.getCartItemRequests();
        Cart cart = Cart.cartCreate();
        return makeCart(cart,cartItemRequests);
    }

    private Cart makeCart(Cart cart, List<CartItemRequest> cartItemRequests) {
        for (CartItemRequest cartItemRequest : cartItemRequests) {
            //카트에 총 상품의 수를 증가
            cart.addCountCart(cartItemRequest.getCount());
            Item drink = itemRepository
                    .findByNameAndHotIce(cartItemRequest.getName(), cartItemRequest.getStatus());

            cart.addTotalPrice(drink.getCost() * cartItemRequest.getCount());
            //request 들어온 상품을 찾고 cartItem 에 등록
            Item findItem = itemRepository
                    .findByNameAndHotIce(cartItemRequest.getName(), cartItemRequest.getStatus());
            CartItem cartItem = CartItem
                    .createCartItem(cart, findItem, cartItemRequest.getCount());
            cart.addCartItem(cartItem);
            cartItemRepository.save(cartItem);
        }
        return cartRepository.save(cart);
    }

    /**
     * 카트 불러와서 삭제 과정 논의 어떤 기준으로 불러와서 진행을 할까?
     * 1. 카트 아이디와 회원 아이디?
     * 2.
     */
    @Override
    @Transactional
    public void deleteCartItem(CartDeleteRequest request){
        Optional<Cart> findCart = cartRepository.findById(request.getCartId());
        if (findCart.isEmpty()){
            throw new CartNotFoundException("존재하지 않는 장바구니 입니다.");
        }
        Cart cart = findCart.get();
        List<CartItem> cartItemEntities = cart.getCartItem();



    }
}
