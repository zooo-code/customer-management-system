package com.example.cms.member.service;

import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void 회원을_저장할_수_있다(){
        //given
        MemberCreate park = MemberCreate.builder()
                .phone("12345")
                .name("park")
                .build();
        //when
        Member save = memberService.save(park);

        //then
        assertThat(save.getName()).isEqualTo(park.getName());
        assertThat(save.getPhone()).isEqualTo(park.getPhone());
        assertThat(save.getStatus()).isEqualTo(EMemberStatus.OPEN);
    }

    @Test
    public void 회원정보를_수정할_수_있다(){
        //given
        MemberCreate kim = MemberCreate.builder()
                .phone("1234")
                .name("kim")
                .build();
        memberService.save(kim);
        MemberUpdate lee = MemberUpdate.builder()
                .status(EMemberStatus.BLIND)
                .name("lee")
                .phone("12346")
                .build();
        //when
        Member member = memberService.memberUpdate("1234", lee);

        Member membership = memberService.findMembership("12346");

        Member membership1 = memberService.findMembership("1234");
        System.out.println("membership1 = " + membership1.getId());
        System.out.println("member.getId() = " + member.getId());
        //then
        assertThat(membership.getName()).isEqualTo(member.getName());
        assertThat(membership.getPhone()).isEqualTo(member.getPhone());
        assertThat(membership.getStatus()).isEqualTo(EMemberStatus.BLIND);
    }

    @Test
    public void 회원정보를_찾을_수_있다(){
        //given
        MemberCreate kim = MemberCreate.builder()
                .phone("1234123")
                .name("cho")
                .build();
        Member save = memberService.save(kim);

        //when
        Member member = memberService.findMembership("1234123");

        //then
        assertThat(save.getName()).isEqualTo(member.getName());
        assertThat(save.getPhone()).isEqualTo(member.getPhone());
        assertThat(save.getStatus()).isEqualTo(EMemberStatus.OPEN);
    }


}