package com.example.cms.member.repository;

import com.example.cms.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByMobile(String mobile);

    Optional<Member> findById(Long id);
}
