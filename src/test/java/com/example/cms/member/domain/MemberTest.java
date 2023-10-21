package com.example.cms.member.domain;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Test
    public void MemberCreate_객체로_생성할_수_있다(){
        //given
        MemberCreate kim = MemberCreate.builder()
                .phone("1234")
                .name("kim")
                .build();
        //when
        Member member = Member.from(kim);
        //then
        assertThat(member.getId()).isNull();
        assertThat(member.getName()).isEqualTo("kim");
    }

    @Test
    public void MemberUpdate_객체로_업데이트를_할_수_있다(){
        //given
        Member lee = Member.builder()
                .id(1L)
                .status(EMemberStatus.OPEN)
                .membershipPoint(1000)
                .name("lee")
                .phone("1234")
                .build();
        MemberUpdate lee_kim = MemberUpdate.builder()
                .phone("12345")
                .name("lee_kim")
                .build();
        //when
        Member update = lee.update(lee_kim);
        //then
        assertThat(update.getId()).isEqualTo(1L);
        assertThat(update.getName()).isEqualTo("lee_kim");
        assertThat(update.getPhone()).isEqualTo("12345");

    }


}