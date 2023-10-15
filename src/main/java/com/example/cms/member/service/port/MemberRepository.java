package com.example.cms.member.service.port;

import com.example.cms.member.domain.Member;
import com.example.cms.member.infrastructure.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {

    Member save(Member member);

    Optional<MemberEntity> findByMobile(String mobile);

    Optional<MemberEntity> findById(Long id);
}
