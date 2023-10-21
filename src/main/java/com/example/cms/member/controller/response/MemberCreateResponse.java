package com.example.cms.member.controller.response;


import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateResponse {

    private String name;
    private String phone;
    private Integer myPoint;

    public MemberCreateResponse(String name, String phone, Integer myPoint) {
        this.name = name;
        this.phone = phone;
        this.myPoint = myPoint;
    }

    public static MemberCreateResponse from(Member member) {

        return MemberCreateResponse.builder()
                .phone(member.getMobile())
                .name(member.getName())
                .build();
    }
}
