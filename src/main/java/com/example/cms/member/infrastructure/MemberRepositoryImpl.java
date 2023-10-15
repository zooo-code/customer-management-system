package com.example.cms.member.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.cms.member.domain.QMember.*;

@Transactional
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<MemberEntity> findMemberList(){
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    @Override
    public Page<MemberEntity> memberPage(Pageable pageable) {
        List<MemberEntity> memberEntityList = queryFactory
                .selectFrom(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        int total = queryFactory
                .selectFrom(member)
                .fetch()
                .size();

        return new PageImpl<>(memberEntityList,pageable,total);
    }

}
