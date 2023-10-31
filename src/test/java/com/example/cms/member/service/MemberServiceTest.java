package com.example.cms.member.service;


import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;
import com.example.cms.member.exception.MemberAlreadyExistException;
import com.example.cms.member.exception.MemberNotFoundException;
import com.example.cms.mock.FakeMemberRepository;
import com.example.cms.mock.TestClockHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {

    private MemberServiceImpl memberService;

    @BeforeEach
    void init(){
        FakeMemberRepository fakeMemberRepository = new FakeMemberRepository();
        this.memberService = MemberServiceImpl.builder()
                .memberRepository(fakeMemberRepository)
                .clockHolder(new TestClockHolder(1L))
                .build();
        fakeMemberRepository.save(Member.builder()
                .name("test")
                .phone("1234")
                .status(EMemberStatus.OPEN)
                        .membershipPoint(0)
                .build());
        fakeMemberRepository.save(Member.builder()
                .name("test1")
                .phone("12341235")
                .status(EMemberStatus.OPEN)
                .membershipPoint(0)
                .build());

    }


    @Test
    public void MemberCreate_회원을_저장할_수_있다(){
        //given
        MemberCreate park = MemberCreate.builder()
                .phone("12345")
                .name("park")
                .build();
        Member from = Member.from(park, new TestClockHolder(1L));
        //when
        Member save = memberService.save(park);

        //then
        assertThat(save.getName()).isEqualTo(park.getName());
        assertThat(save.getPhone()).isEqualTo(park.getPhone());
        assertThat(save.getStatus()).isEqualTo(EMemberStatus.OPEN);
    }

    @Test
    public void MemberCreate_이미_존재하는_회원은_저장할_수_없다(){
        //given
        MemberCreate park = MemberCreate.builder()
                .phone("1234")
                .name("park")
                .build();
        //when


        //then
        assertThatThrownBy(() -> memberService.save(park))
                .isInstanceOf(MemberAlreadyExistException.class);
    }

    @Test
    public void MemberUpdate_로_회원정보를_수정할_수_있다(){
        //given

        MemberUpdate lee = MemberUpdate.builder()
                .name("lee")
                .phone("12346")
                .build();
        //when
        Member member = memberService.memberUpdate("1234", lee);

        //then
        assertThat(member.getName()).isEqualTo(lee.getName());
        assertThat(member.getPhone()).isEqualTo(lee.getPhone());
    }

    @Test
    public void mobile_로_회원정보를_찾을_수_있다(){
        //given
        String phone = "1234";

        //when
        Member member = memberService.findMembership(phone);

        //then
        assertThat("test").isEqualTo(member.getName());
        assertThat("1234").isEqualTo(member.getPhone());
        assertThat(EMemberStatus.OPEN).isEqualTo(member.getStatus());
    }

    @Test
    public void 없는_번호는_mobile_로_회원정보를_찾을_수_있다(){
        //given
        String phone = "1234123123";

        //when

        //then
        assertThatThrownBy(()-> memberService.findMembership(phone)).isInstanceOf(MemberNotFoundException.class);
    }


}