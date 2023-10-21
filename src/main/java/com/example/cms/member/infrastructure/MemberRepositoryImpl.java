package com.example.cms.member.infrastructure;


import com.example.cms.member.domain.Member;
import com.example.cms.member.service.port.MemberRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberRepositoryJpa memberJpaRepository;


    public MemberRepositoryImpl(MemberRepositoryJpa memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.from(member)).toModel();
    }

    @Override
    public Optional<Member> findByMobile(String mobile) {
         return memberJpaRepository.findByMobile(mobile).map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id).map(MemberEntity::toModel);
    }




}
