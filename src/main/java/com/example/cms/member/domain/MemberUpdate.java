package com.example.cms.member.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdate {

    private final String name;
    private final String phone;
    private final EMemberStatus status;

    @Builder
    public MemberUpdate(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("status") EMemberStatus status) {
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
