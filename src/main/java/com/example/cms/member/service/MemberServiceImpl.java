package com.example.cms.member.service;

import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.service.port.MemberRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Builder
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ClockHolder clockHolder;
    @Override
    @Transactional
    public Member save(MemberCreate memberCreate){
        if (memberRepository.findByMobile(memberCreate.getPhone()).isPresent()){
            throw new MemberAlreadyExistException("member already exist");
        }
        return memberRepository.save(Member.from(memberCreate,clockHolder));

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
        Member update = member.update(memberUpdate,clockHolder);
        return memberRepository.save(update);
    }



}
