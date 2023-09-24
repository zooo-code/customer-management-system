package com.example.cms.member.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String mobile;
    private String name;
    private Integer membershipPoint;
    @Builder
    public MemberResponse(String mobile, String name, Integer membershipPoint) {
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
    }

}
