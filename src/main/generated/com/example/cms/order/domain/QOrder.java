package com.example.cms.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -2022063110L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.example.cms.utils.entity.QBaseDateTimeEntity _super = new com.example.cms.utils.entity.QBaseDateTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final com.example.cms.cart.infrastructure.QCartEntity cartEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.cms.member.infrastructure.QMemberEntity memberEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath ordersId = createString("ordersId");

    public final NumberPath<Integer> ordersPrice = createNumber("ordersPrice", Integer.class);

    public final EnumPath<EPayments> payment = createEnum("payment", EPayments.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cartEntity = inits.isInitialized("cartEntity") ? new com.example.cms.cart.infrastructure.QCartEntity(forProperty("cartEntity"), inits.get("cartEntity")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new com.example.cms.member.infrastructure.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

