package com.example.cms.member.repository;

import com.example.cms.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberList();

    Page<Member> memberPage(Pageable pageable);
}
