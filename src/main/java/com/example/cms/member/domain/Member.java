package com.example.cms.member.domain;

import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.infrastructure.EMemberStatus;
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

    public Member from(MemberCreateRequest memberCreateRequest){
        return Member.builder()
                .mobile(memberCreateRequest.getMobile())
                .name(memberCreateRequest.getName())
                .status(EMemberStatus.OPEN)
                .build();
    }
    public void update(String name, String phoneNumber){
        this.mobile = phoneNumber;
        this.name = name;
    }
}
