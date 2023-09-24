package com.example.cms.member.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class MemberUpdateResponse {

    private String mobile;
    private String name;
    @Builder
    public MemberUpdateResponse(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }
}
