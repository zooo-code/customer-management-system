package com.example.cms.member.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


//쿼리 dsl 만드는 곳
@Repository
@RequiredArgsConstructor
@Transactional
public class MemberQDSLRepository implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;





}
