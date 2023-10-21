package com.example.cms.cart.controller;

import com.example.cms.cart.controller.request.CartDeleteRequest;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.controller.response.CartResponse;
import com.example.cms.cart.service.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CartEntity", description = "카트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {


    private final CartServiceImpl cartServiceImpl;

    @Operation(summary = "장바구니 생성", description = "징바구니를 생성합니다.")
    @PostMapping("/create")
    public CartResponse CreateCart(@RequestBody CartRequest request){
        return cartServiceImpl.CreateCart(request);
    }

    /**
     * 카트 불러와서 삭제 과정 논의 어떤 기준으로 불러와서 진행을 할까?
     * 1. 카트 아이디와 회원 아이디?
     */
    @PostMapping("/deleteItem")
    public void deleteCartItem(@RequestBody CartDeleteRequest cartDeleteRequest){


    }


}
