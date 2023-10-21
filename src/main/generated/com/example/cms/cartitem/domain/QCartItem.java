package com.example.cms.cartitem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.cms.cartitem.infrastructure.CartItemEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartItem is a Querydsl query type for CartItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartItem extends EntityPathBase<CartItemEntity> {

    private static final long serialVersionUID = -671098376L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartItem cartItem = new QCartItem("cartItem");

    public final com.example.cms.cart.domain.QCart cart;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.cms.item.infrastructure.QItemEntity itemEntity;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QCartItem(String variable) {
        this(CartItemEntity.class, forVariable(variable), INITS);
    }

    public QCartItem(Path<? extends CartItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartItem(PathMetadata metadata, PathInits inits) {
        this(CartItemEntity.class, metadata, inits);
    }

    public QCartItem(Class<? extends CartItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new com.example.cms.cart.domain.QCart(forProperty("cart"), inits.get("cart")) : null;
        this.itemEntity = inits.isInitialized("itemEntity") ? new com.example.cms.item.infrastructure.QItemEntity(forProperty("itemEntity")) : null;
    }

}

