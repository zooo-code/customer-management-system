package com.example.cms.member.controller;

import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.mock.member.MemberTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MemberControllerTest {

    @Test
    @DisplayName("회원 가입 테스트")
    void memberCreateController(){
        MemberTestContainer memberTestContainer = MemberTestContainer
                .builder()
                .clockHolder(()->200L)
                .build();
        MemberCreate test = new MemberCreate("test", "01032311231");
        ResponseEntity<MemberCreateResponse> member = memberTestContainer
                .memberController
                .createMember(test);

        assertThat(member.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(member.getBody()).isNotNull();
        assertThat(member.getBody().getName()).isEqualTo(test.getName());
        assertThat(member.getBody().getPhone()).isEqualTo(test.getPhone());
        assertThat(member.getBody().getMyPoint()).isEqualTo(0);

    }

    @Test
    @DisplayName("회원 번호로 정보를 찾을수 있다")
    void findMemberByPhoneController(){
        MemberTestContainer memberTestContainer = MemberTestContainer
                .builder()
                .clockHolder(()->200L)
                .build();


        Member test = memberTestContainer.memberRepository.save(Member.builder()
                .phone("1234")
                .id(1L)
                .name("test")
                .status(EMemberStatus.OPEN)
                .membershipPoint(0)
                .createAt(1L)
                .build());
        ResponseEntity<MemberResponse> member = memberTestContainer
                .memberController
                .findMembership(test.getPhone());

        assertThat(member.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(member.getBody()).isNotNull();
        assertThat(member.getBody().getName()).isEqualTo(test.getName());
        assertThat(member.getBody().getMobile()).isEqualTo(test.getPhone());
        assertThat(member.getBody().getMembershipPoint()).isEqualTo(0);


    }
}