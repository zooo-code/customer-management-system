package com.example.cms.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private final Long id;
    private final String phone;
    private final String name;
    private final Integer membershipPoint;
    private final EMemberStatus status;


    @Builder
    public Member(Long id, String phone, String name, Integer membershipPoint, EMemberStatus status) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
    }


    public static Member from(MemberCreate memberCreate){
        Integer firstPoint = 0;
        return Member.builder()
                .phone(memberCreate.getPhone())
                .name(memberCreate.getName())
                .membershipPoint(firstPoint)
                .status(EMemberStatus.OPEN)
                .build();
    }
    public Member update(MemberUpdate memberUpdate){
        return Member.builder()
                .id(id)
                .status(status)
                .membershipPoint(membershipPoint)
                .phone(memberUpdate.getPhone())
                .name(memberUpdate.getName())
                .build();
    }
}
