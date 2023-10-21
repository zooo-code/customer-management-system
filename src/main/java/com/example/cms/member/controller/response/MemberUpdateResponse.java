package com.example.cms.member.controller.response;

import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateResponse {

    private final String mobile;
    private final String name;
    @Builder
    public MemberUpdateResponse(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }
    public static MemberUpdateResponse from(Member member){
        return MemberUpdateResponse.builder()
                .mobile(member.getMobile())
                .name(member.getName())
                .build();
    }

}
