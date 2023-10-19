package com.example.cms.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private final Long id;
    private final String mobile;
    private final String name;
    private final Integer membershipPoint;
    private final EMemberStatus status;


    @Builder
    public Member(Long id, String mobile, String name, Integer membershipPoint, EMemberStatus status) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
    }


    public static Member from(MemberCreate memberCreate){
        return Member.builder()
                .mobile(memberCreate.getPhone())
                .name(memberCreate.getName())
                .status(EMemberStatus.OPEN)
                .build();
    }
    public Member update(MemberUpdate memberUpdate){
        return Member.builder()
                .mobile(memberUpdate.getPhone())
                .name(memberUpdate.getName())
                .status(memberUpdate.getStatus())
                .build();
    }
}
