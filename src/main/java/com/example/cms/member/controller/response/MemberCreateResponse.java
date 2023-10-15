package com.example.cms.member.controller.response;

import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateResponse {

    public String name;
    public String phone;

    @Builder
    public MemberCreateResponse(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public MemberCreateResponse from(Member member){
        return MemberCreateResponse.builder()
                .name(member.getName())
                .phone(member.getMobile())
                .build();
    }

}
