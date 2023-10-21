package com.example.cms.item.infrastructure;

import com.example.cms.item.domain.EItemStatus;
import com.example.cms.item.domain.QItem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class ItemRepositoryQDSL implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryQDSL(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ItemEntity> searchItems(ItemEntity filter) {
        return queryFactory.selectFrom(QItem.item)
                .where(containName(filter.getName()),
                        eqCost(filter.getCost()),
                        eqHotIce(filter.getHotIce())
                )
                .fetch();
    }

    @Override
    public PageImpl<ItemEntity> searchItemsPaging(Pageable pageable, ItemEntity filter) {
        List<ItemEntity> itemEntities = queryFactory.select(QItem.item)
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

        return new PageImpl<>(itemEntities, pageable, count);
    }

    private BooleanExpression containName(String name){
        return hasText(name) ? QItem.item.name.contains(name) : null ;
    }

    private BooleanExpression eqCost(Integer cost){
        return cost != null ? QItem.item.cost.eq(cost) : null ;
    }

    private BooleanExpression eqHotIce(EItemStatus hotIce){
        return hotIce != null ? QItem.item.hotIce.eq(hotIce) : null;
    }
}
