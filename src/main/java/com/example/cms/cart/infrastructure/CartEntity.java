package com.example.cms.cart.infrastructure;


import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.cartitem.infrastructure.CartItemEntity;
import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    @Column(name ="count")
    private Integer count;

    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToMany(mappedBy = "cartEntity")
    private List<CartItemEntity> cartItemEntities = new ArrayList<>();

    private LocalDateTime createdAt;
    @Builder
    public CartEntity(Long id, MemberEntity memberEntity, Integer count, Integer totalPrice, List<CartItemEntity> cartItemEntities, LocalDateTime createdAt) {
        this.id = id;
        this.memberEntity = memberEntity;
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItemEntities = cartItemEntities;
        this.createdAt = createdAt;
    }
    public static CartEntity from(Cart cart){
        CartEntity cartEntity = new CartEntity();
        cartEntity.cartItemEntities = cart.getCartItem()
                .stream()
                .map(CartItemEntity::from)
                .collect(Collectors.toList());

        cartEntity.count = cart.getCount();
        cartEntity.totalPrice = cart.getTotalPrice();
        cartEntity.memberEntity = MemberEntity.from(cart.getMember());
        return cartEntity;
    }
//    public static CartEntity createCart(MemberEntity memberEntity){
//        return new CartEntity(memberEntity,0,LocalDateTime.now(),0);
//    }
    public Cart toModel(){
        return Cart.builder()
                .id(id)
                .cartItem(cartItemEntities.stream()
                        .map(CartItemEntity::toModel).collect(Collectors.toList()))
                .totalPrice(totalPrice)
                .member(memberEntity.toModel())
                .count(count)
                .createdAt(createdAt)
                .build();
    }
    public void addCountCart(Integer count){
        this.count += count;
    }

    public void addTotalPrice(Integer price){
        this.totalPrice += price;
    }
}
