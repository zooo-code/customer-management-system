package com.example.cms.member.service;

import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;
import com.example.cms.member.domain.Member;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.member.infrastructure.MemberJpaRepository;
import com.example.cms.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberCreateResponse save(MemberCreateRequest memberCreateRequest){
        if (memberRepository.findByMobile(memberCreateRequest.getMobile()).isPresent()){
            throw new MemberAlreadyExistException("member already exist");
        }
        Member saveMember = memberRepository.save(memberCreateRequest.toEntity().toModel());
        saveMember.firstPoint();
        return new MemberCreateResponse(saveMember.getName(), saveMember.getMobile());
    }
    @Override
    @Transactional(readOnly = true)
    public MemberResponse findMembership(String mobile){

        Member member = memberRepository
                .findByMobile(mobile)
                .orElseThrow(() -> new MemberNotFoundException("memberEntity not found")).toModel();

        return new MemberResponse(member.getMobile(), member.getName(), member.getMembershipPoint());
    }
    @Override
    @Transactional
    public MemberUpdateResponse memberUpdate(String previousPhone, MemberUpdateRequest request){
        Member member = memberRepository
                .findByMobile(previousPhone)
                .orElseThrow(() -> new MemberNotFoundException("memberEntity not found")).toModel();

        member.update(request.getName() ,request.getMobile());
        return new MemberUpdateResponse(member.getMobile(), member.getName());
    }
    @Override
    @Transactional(readOnly = true)
    public void findMemberList(){
        
    }

    /**
     * 블라인드 할 회원 정책 필요
     */
    @Override
    @Transactional(readOnly = true)
    public MemberPageResponse memberListPaging(Pageable pageable){

        Page<MemberEntity> members = memberJpaRepository.memberPage(pageable);



        return new MemberPageResponse();
    }



}
