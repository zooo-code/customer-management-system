package com.example.cms.member.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.cms.member.infrastructure.QMemberEntity.*;


//쿼리 dsl 만드는 곳
@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepositoryQDSL implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;





}
