package com.example.cms.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.cms.member.infrastructure.EMemberStatus;
import com.example.cms.member.infrastructure.MemberEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 902648102L;

    public static final QMember member = new QMember("member1");

    public final com.example.cms.utils.entity.QBaseDateTimeEntity _super = new com.example.cms.utils.entity.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> firstJoinPoint = createNumber("firstJoinPoint", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> membershipPoint = createNumber("membershipPoint", Integer.class);

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final EnumPath<EMemberStatus> status = createEnum("status", EMemberStatus.class);

    public QMember(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMember(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

