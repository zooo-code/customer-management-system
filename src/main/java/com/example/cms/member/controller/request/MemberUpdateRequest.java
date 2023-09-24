package com.example.cms.member.controller.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @NotBlank
    private String mobile;

    @NotBlank
    private String name;

    @Builder
    public MemberUpdateRequest(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }
}
