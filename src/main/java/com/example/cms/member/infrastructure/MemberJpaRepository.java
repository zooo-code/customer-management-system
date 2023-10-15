package com.example.cms.member.infrastructure;

import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.member.infrastructure.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {

    Optional<MemberEntity> findByMobile(String mobile);

    Optional<MemberEntity> findById(Long id);
}
