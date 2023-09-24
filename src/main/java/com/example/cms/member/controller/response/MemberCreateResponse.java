package com.example.cms.member.controller.response;

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


}
