package com.example.cms.member.service;

import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.infrastructure.MemberRepository;
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
        MemberEntity saveMemberEntity = memberRepository.save(memberCreateRequest.toEntity());
        saveMemberEntity.firstPoint();
        return new MemberCreateResponse(saveMemberEntity.getName(), saveMemberEntity.getMobile());
    }

    @Transactional(readOnly = true)
    public MemberResponse findMembership(String mobile){
        MemberEntity memberEntity = memberRepository
                .findByMobile(mobile)
                .orElseThrow(()->new MemberNotFoundException("memberEntity not found"));
        return new MemberResponse(memberEntity.getMobile(), memberEntity.getName(), memberEntity.getMembershipPoint());
    }

    @Transactional
    public MemberUpdateResponse memberUpdate(String previousPhone, MemberUpdateRequest request){
        MemberEntity memberEntity = memberRepository
                .findByMobile(previousPhone)
                .orElseThrow(()->new MemberNotFoundException("memberEntity not found"));
        memberEntity.update(request.getName() ,request.getMobile());
        return new MemberUpdateResponse(memberEntity.getMobile(), memberEntity.getName());
    }

    @Transactional(readOnly = true)
    public void findMemberList(){
        
    }

    /**
     * 블라인드 할 회원 정책 필요
     */

    @Transactional(readOnly = true)
    public MemberPageResponse memberListPaging(Pageable pageable){

        Page<MemberEntity> members = memberRepository.memberPage(pageable);



        return new MemberPageResponse();
    }



}
