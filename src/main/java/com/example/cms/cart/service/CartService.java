package com.example.cms.cart.service;

import com.example.cms.cart.controller.request.CartDeleteRequest;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.controller.response.CartResponse;
import com.example.cms.cart.domain.Cart;
import com.example.cms.cart.exception.CartNotFoundException;
import com.example.cms.cart.repository.CartRepository;
import com.example.cms.cartitem.controller.request.CartItemCreateRequest;
import com.example.cms.cartitem.controller.request.CartItemDeleteRequest;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.cartitem.repository.CartItemRepository;
import com.example.cms.item.domain.Item;
import com.example.cms.item.repository.ItemRepository;
import com.example.cms.member.domain.Member;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CartService {

    private final CartRepository cartRepository;
    public final ItemRepository itemRepository;
    public final CartItemRepository cartItemRepository;
    public final MemberRepository memberRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository,
                       CartItemRepository cartItemRepository, MemberRepository memberRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.cartItemRepository = cartItemRepository;
        this.memberRepository = memberRepository;
    }
    /**
     * 메뉴를 선택한 후에 메뉴들(리스트로 들어옴)이 카드에 담긴다.
     */
    @Transactional
    public CartResponse CreateCart(CartRequest request){
        //멤버 못찾으면 예외 처리
        if (memberRepository.findByMobile(request.getPhone()).isEmpty()) {
            throw new MemberNotFoundException("찾을 수 없는 회원입니다.");
        }
        Member member = memberRepository.findByMobile(request.getPhone()).get();
        //카트 생성 멤버
        Cart cart = Cart.createCart(member);
        Cart saveCart = cartRepository.save(cart);

        List<CartItemCreateRequest> cartItemCreateRequests = request.getCartItemRequests();

        for (CartItemCreateRequest cartItemCreateRequest : cartItemCreateRequests) {
            //카트에 총 상품의 수를 증가
            saveCart.addCountCart(cartItemCreateRequest.getCount());
            Item drink = itemRepository.findByNameAndHotIce(cartItemCreateRequest.getName(), cartItemCreateRequest.getStatus());
            saveCart.addTotalPrice(drink.getCost()*cartItemCreateRequest.getCount());
            //request 들어온 상품을 찾고 cartItem 에 등록
            Item findItem = itemRepository
                    .findByNameAndHotIce(cartItemCreateRequest.getName(), cartItemCreateRequest.getStatus());
            CartItem cartItem = CartItem
                    .createCartItem(saveCart, findItem, findItem.getCost(), cartItemCreateRequest.getCount());
            cartItemRepository.save(cartItem);
        }
        Cart save = cartRepository.save(saveCart);

        return new CartResponse(save.getCount(),save.getTotalPrice(),save.getId(),save.getMember().getId());
    }

    /**
     * 카트 불러와서 삭제 과정 논의 어떤 기준으로 불러와서 진행을 할까?
     * 1. 카트 아이디와 회원 아이디?
     * 2.
     */
    @Transactional
    public void deleteCartItem(CartDeleteRequest request){
        Optional<Cart> findCart = cartRepository.findById(request.getCartId());
        if (findCart.isEmpty()){
            throw new CartNotFoundException("존재하지 않는 장바구니 입니다.");
        }
        Cart cart = findCart.get();
        List<CartItem> cartItems = cart.getCartItems();



    }
}
