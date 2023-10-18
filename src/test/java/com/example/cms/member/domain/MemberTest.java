package com.example.cms.member.domain;

import com.example.cms.member.controller.request.MemberCreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Test
    public void MemberCreate_객체로_생성할_수_있다(){
        //given
        MemberCreateRequest kim = MemberCreateRequest.builder()
                .mobile("1234")
                .name("kim")
                .build();
        //when
        Member member = Member.from(kim);
        //then
        assertThat(member.getId()).isNull();
        assertThat(member.getName()).isEqualTo("kim");
    }

}