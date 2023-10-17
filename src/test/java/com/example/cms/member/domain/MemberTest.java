package com.example.cms.member.domain;

import com.example.cms.member.controller.request.MemberCreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Test
    public void 회원가입_테스트(){
        //given
        MemberCreateRequest kim = MemberCreateRequest.builder()
                .mobile("1234")
                .name("kim")
                .build();
        //when
        Member member = Member.from(kim);
        //then
        Assertions.assertThat(member.getId()).isNull();
    }

}