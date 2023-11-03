package com.example.cms.member.domain;


import com.example.cms.mock.TestClockHolder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MemberTest {

    @Test
    public void MemberCreate_객체로_생성할_수_있다(){
        //given
        MemberCreate kim = MemberCreate.builder()
                .phone("1234")
                .name("kim")
                .build();
        //when
        Member member = Member.from(kim,new TestClockHolder(1L));
        //then
        assertThat(member.getId()).isNull();
        assertThat(member.getName()).isEqualTo("kim");
        assertThat(member.getCreateAt()).isEqualTo(1L);
        assertThat(member.getStatus()).isEqualTo(EMemberStatus.OPEN);
        assertThat(member.getMembershipPoint()).isEqualTo(0);

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
                .status(EMemberStatus.BLIND)
                .build();
        //when
        Member update = lee.update(lee_kim,new TestClockHolder(2L));
        //then
        assertThat(update.getId()).isEqualTo(1L);
        assertThat(update.getName()).isEqualTo("lee_kim");
        assertThat(update.getPhone()).isEqualTo("12345");
        assertThat(update.getModifiedAt()).isEqualTo(2L);
        assertThat(update.getStatus()).isEqualTo(EMemberStatus.BLIND);

    }

    @Test
    public void updatePoint(){
        //given
        Member lee = Member.builder()
                .id(1L)
                .status(EMemberStatus.OPEN)
                .membershipPoint(1000)
                .name("lee")
                .phone("1234")
                .build();
        ;
        //when
        lee.updatePoint(100);
        //then
        assertThat(lee.getMembershipPoint()).isEqualTo(100);

    }
}