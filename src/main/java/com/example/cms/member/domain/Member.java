package com.example.cms.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String mobile;
    private  String name;
    private Integer membershipPoint;
    private EMemberStatus status;
    Integer firstJoinPoint = 10000;

    @Builder
    public Member(Long id, String mobile, String name, Integer membershipPoint, EMemberStatus status) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
    }

    public void firstPoint(){
        this.membershipPoint = firstJoinPoint;
    }

    public void updatePoint(Integer membershipPoint){
        this.membershipPoint = membershipPoint;
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
