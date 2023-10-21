package com.example.cms.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.cms.item.infrastructure.ItemEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for ItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<ItemEntity> {

    private static final long serialVersionUID = 1858762008L;

    public static final QItem item = new QItem("item");

    public final com.example.cms.utils.entity.QBaseDateTimeEntity _super = new com.example.cms.utils.entity.QBaseDateTimeEntity(this);

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<EItemStatus> hotIce = createEnum("hotIce", EItemStatus.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QItem(String variable) {
        super(ItemEntity.class, forVariable(variable));
    }

    public QItem(Path<? extends ItemEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(ItemEntity.class, metadata);
    }

}

