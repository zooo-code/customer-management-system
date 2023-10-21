package com.example.cms.member.service;

import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member save(MemberCreate memberCreate){
        if (memberRepository.findByMobile(memberCreate.getPhone()).isPresent()){
            throw new MemberAlreadyExistException("member already exist");
        }
        return memberRepository.save(Member.from(memberCreate));

    }
    @Override
    @Transactional(readOnly = true)
    public Member findMembership(String mobile){

        return memberRepository
                .findByMobile(mobile)
                .orElseThrow(() -> new MemberNotFoundException("memberEntity not found"));
    }
    @Override
    @Transactional
    public Member memberUpdate(String previousPhone, MemberUpdate memberUpdate){
        Member member = memberRepository
                .findByMobile(previousPhone)
                .orElseThrow(() -> new MemberNotFoundException("memberEntity not found"));

        return member.update(memberUpdate);
    }



}
