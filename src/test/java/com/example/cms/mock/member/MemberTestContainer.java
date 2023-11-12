package com.example.cms.mock.member;

import com.example.cms.member.controller.MemberController;
import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.service.MemberServiceImpl;
import com.example.cms.member.service.port.MemberRepository;
import com.example.cms.mock.member.FakeMemberRepository;
import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;

public class MemberTestContainer {

    public final MemberRepository memberRepository;

    public final MemberService memberService;
    public final MemberController memberController;

    @Builder
    public MemberTestContainer(ClockHolder clockHolder ) {
        this.memberRepository = new FakeMemberRepository();
        this.memberService = MemberServiceImpl
                .builder()
                .memberRepository(this.memberRepository)
                .clockHolder(clockHolder)
                .build();
        this.memberController = MemberController.builder()
                .memberService(this.memberService)
                .build();

    }
}
