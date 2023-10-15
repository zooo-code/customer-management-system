package com.example.cms.member.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberEntity> findMemberList();

    Page<MemberEntity> memberPage(Pageable pageable);
}
