package com.example.cms.cart.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class CartRepositoryQDSL {

    private final JPAQueryFactory queryFactory;
}
