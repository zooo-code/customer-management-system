package com.example.cms.member.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreate {

    private final String name;
    private final String phone;

    @Builder
    public MemberCreate(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone) {
        this.name = name;
        this.phone = phone;
    }
}
