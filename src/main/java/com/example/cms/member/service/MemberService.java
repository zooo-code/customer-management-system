package com.example.cms.member.service;

import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;
import com.example.cms.member.domain.Member;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public MemberCreateResponse save(MemberCreateRequest memberCreateRequest){
        if (memberRepository.findByMobile(memberCreateRequest.getMobile()).isPresent()){
            throw new MemberAlreadyExistException("member already exist");
        }
        Member saveMember = memberRepository.save(memberCreateRequest.toEntity());
        saveMember.firstPoint();
        return new MemberCreateResponse(saveMember.getName(),saveMember.getMobile());
    }

    @Transactional(readOnly = true)
    public MemberResponse findMembership(String mobile){
        Member member = memberRepository
                .findByMobile(mobile)
                .orElseThrow(()->new MemberNotFoundException("member not found"));
        return new MemberResponse(member.getMobile(),member.getName(), member.getMembershipPoint());
    }

    @Transactional
    public MemberUpdateResponse memberUpdate(String previousPhone, MemberUpdateRequest request){
        Member member = memberRepository
                .findByMobile(previousPhone)
                .orElseThrow(()->new MemberNotFoundException("member not found"));
        member.update(request.getName() ,request.getMobile());
        return new MemberUpdateResponse(member.getMobile(),member.getName());
    }

    @Transactional(readOnly = true)
    public void findMemberList(){
        
    }

    /**
     * 블라인드 할 회원 정책 필요
     */

    @Transactional(readOnly = true)
    public MemberPageResponse memberListPaging(Pageable pageable){

        Page<Member> members = memberRepository.memberPage(pageable);



        return new MemberPageResponse();
    }



}
