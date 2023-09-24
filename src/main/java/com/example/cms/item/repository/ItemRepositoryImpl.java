package com.example.cms.item.repository;

import com.example.cms.item.domain.Item;
import com.example.cms.item.domain.ItemStatus;
import com.example.cms.item.domain.QItem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.util.StringUtils.getFilename;
import static org.springframework.util.StringUtils.hasText;

public class ItemRepositoryImpl implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Item> searchItems(Item filter) {
        return queryFactory.selectFrom(QItem.item)
                .where(containName(filter.getName()),
                        eqCost(filter.getCost()),
                        eqHotIce(filter.getHotIce())
                )
                .fetch();
    }

    @Override
    public PageImpl<Item> searchItemsPaging(Pageable pageable, Item filter) {
        List<Item> items = queryFactory.select(QItem.item)
                .from(QItem.item)
                .where(containName(filter.getName()),
                        eqCost(filter.getCost()),
                        eqHotIce(filter.getHotIce())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(QItem.item.count())
                .from(QItem.item)
                .where(containName(filter.getName()),
                        eqCost(filter.getCost()),
                        eqHotIce(filter.getHotIce())
                )
                .fetchOne();

        return new PageImpl<>(items, pageable, count);
    }

    private BooleanExpression containName(String name){
        return hasText(name) ? QItem.item.name.contains(name) : null ;
    }

    private BooleanExpression eqCost(Integer cost){
        return cost != null ? QItem.item.cost.eq(cost) : null ;
    }

    private BooleanExpression eqHotIce(ItemStatus hotIce){
        return hotIce != null ? QItem.item.hotIce.eq(hotIce) : null;
    }
}
