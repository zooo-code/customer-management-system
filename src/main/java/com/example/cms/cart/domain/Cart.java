package com.example.cms.cart.domain;


import com.example.cms.member.domain.Member;
import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer count;

    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    private LocalDateTime createdAt;

    public Cart(Member member, Integer count, LocalDateTime localDateTime,Integer totalPrice) {
        this.member = member;
        this.count = count;
        this.createdAt = localDateTime;
        this.totalPrice = totalPrice;
    }

    public static Cart createCart(Member member){
        return new Cart(member,0,LocalDateTime.now(),0);
    }

    public void addCountCart(Integer count){
        this.count += count;
    }

    public void addTotalPrice(Integer price){
        this.totalPrice += price;
    }
}
