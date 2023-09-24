package com.example.cms.member.controller.request;

import com.example.cms.member.domain.Member;

import com.example.cms.member.domain.MemberStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class MemberCreateRequest {

    @NotEmpty
    private String mobile;

    @NotBlank
    private String name;
    private LocalDateTime createDate;
    private MemberStatus status;
    public Member toEntity(){
        return Member.builder()
                .mobile(mobile)
                .name(name)
                .status(MemberStatus.OPEN)
                .build();
    }
    @Builder
    public MemberCreateRequest(String mobile, String name,  MemberStatus status) {
        this.mobile = mobile;
        this.name = name;
        this.status = status;
    }
}
