package com.example.cms.member.infrastructure;

import com.example.cms.member.domain.Member;
import com.example.cms.member.service.port.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.cms.member.infrastructure.QMemberEntity.*;


//쿼리 dsl 만드는 곳
@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberRepositoryCustom, MemberRepository {

    private final JPAQueryFactory queryFactory;
    private final MemberJpaRepository memberJpaRepository;


    public List<MemberEntity> findMemberList(){
        return queryFactory
                .selectFrom(memberEntity)
                .fetch();
    }

    @Override
    public Page<MemberEntity> memberPage(Pageable pageable) {
        List<MemberEntity> memberEntityList = queryFactory
                .selectFrom(memberEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        int total = queryFactory
                .selectFrom(memberEntity)
                .fetch()
                .size();

        return new PageImpl<>(memberEntityList,pageable,total);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.from(member)).toModel();
    }

    @Override
    public Optional<MemberEntity> findByMobile(String mobile) {
        return Optional.empty();
    }

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return Optional.empty();
    }
}
