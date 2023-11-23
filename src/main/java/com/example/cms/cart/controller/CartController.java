package com.example.cms.cart.controller;

import com.example.cms.cart.controller.port.CartService;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.controller.response.CartResponse;
import com.example.cms.cart.domain.Cart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CartEntity", description = "카트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {


    private final CartService cartService;

    @Operation(summary = "장바구니 생성", description = "징바구니를 생성합니다.")
    @PostMapping("/create")
    public ResponseEntity<CartResponse> CreateCart(@RequestBody CartRequest request){
        return ResponseEntity.ok()
                .body(CartResponse
                        .from(cartService.CreateCart(request)));
    }

    @Operation(summary = "장바구니 삭제", description = "징바구니를 삭제합니다.")
    @PostMapping("/deleteCart/{id}")
    public void deleteCartItem(@PathVariable Long id){
        cartService.deleteCart(id);
    }


}
