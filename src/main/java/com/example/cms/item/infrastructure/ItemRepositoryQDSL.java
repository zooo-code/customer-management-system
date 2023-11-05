package com.example.cms.item.infrastructure;

import com.example.cms.item.domain.EItemStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryQDSL implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;



    @Override
    public List<ItemEntity> searchItems(String name, Integer cost, EItemStatus eItemStatus) {
        return queryFactory.selectFrom(QItemEntity.itemEntity)
                .where(containName(name),
                        eqCost(cost),
                        eqHotIce(eItemStatus)
                )
                .fetch();
    }
//
//    @Override
//    public PageImpl<ItemEntity> searchItemsPaging(Pageable pageable, ItemEntity filter) {
//        List<ItemEntity> itemEntities = queryFactory.select(QItemEntity.itemEntity)
//                .from(QItemEntity.itemEntity)
//                .where(containName(filter.getName()),
//                        eqCost(filter.getCost()),
//                        eqHotIce(filter.getHotIce())
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = queryFactory.select(QItemEntity.itemEntity.count())
//                .from(QItemEntity.itemEntity)
//                .where(containName(filter.getName()),
//                        eqCost(filter.getCost()),
//                        eqHotIce(filter.getHotIce())
//                )
//                .fetchOne();
//
//        return new PageImpl<>(itemEntities, pageable, count);
//    }
//
    private BooleanExpression containName(String name){
        return hasText(name) ? QItemEntity.itemEntity.name.contains(name) : null ;
    }

    private BooleanExpression eqCost(Integer cost){
        return cost != null ? QItemEntity.itemEntity.cost.eq(cost) : null ;
    }

    private BooleanExpression eqHotIce(EItemStatus hotIce){
        return hotIce != null ? QItemEntity.itemEntity.hotIce.eq(hotIce) : null;
    }
}
