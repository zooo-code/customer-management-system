package com.example.cms.member.controller.request;

import com.example.cms.member.infrastructure.EMemberStatus;
import com.example.cms.member.infrastructure.MemberEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class MemberCreateRequest {

    @NotEmpty
    private String mobile;

    @NotBlank
    private String name;
    private LocalDateTime createDate;
    private EMemberStatus status;
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mobile(mobile)
                .name(name)
                .status(EMemberStatus.OPEN)
                .build();
    }
    @Builder
    public MemberCreateRequest(String mobile, String name,  EMemberStatus status) {
        this.mobile = mobile;
        this.name = name;
        this.status = status;
    }
}
