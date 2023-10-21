package com.example.cms.member.service.port;

import com.example.cms.member.domain.Member;


import java.util.Optional;


public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByMobile(String mobile);

    Optional<Member> findById(Long id);

    Member update();
}
