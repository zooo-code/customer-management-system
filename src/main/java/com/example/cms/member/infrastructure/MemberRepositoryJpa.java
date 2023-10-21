package com.example.cms.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepositoryJpa extends JpaRepository<MemberEntity, Long>, MemberCustomRepository {

    Optional<MemberEntity> findByMobile(String mobile);

    Optional<MemberEntity> findById(Long id);
}
