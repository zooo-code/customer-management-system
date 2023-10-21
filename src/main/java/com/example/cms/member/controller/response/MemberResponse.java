package com.example.cms.member.controller.response;

import com.example.cms.member.domain.Member;
import lombok.Builder;

import lombok.Getter;

@Getter
public class MemberResponse {

    private final String mobile;
    private final String name;
    private final Integer membershipPoint;
    @Builder
    public MemberResponse(String mobile, String name, Integer membershipPoint) {
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
    }

    public static MemberResponse from(Member member){
        return MemberResponse.builder()
                .mobile(member.getMobile())
                .name(member.getName())
                .membershipPoint(member.getMembershipPoint())
                .build();
    }

}
