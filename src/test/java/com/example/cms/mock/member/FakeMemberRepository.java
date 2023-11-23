package com.example.cms.mock.member;


import com.example.cms.member.domain.Member;
import com.example.cms.member.service.port.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberRepository implements MemberRepository {

    private final AtomicLong autoGeneratedId =new AtomicLong(0);
    private final List<Member> data = new ArrayList<>();

    @Override
    public Member save(Member member) {
        if (member.getId() == null || member.getId() == 0){
            Member saveMember = Member.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .phone(member.getPhone())
                    .name(member.getName())
                    .membershipPoint(member.getMembershipPoint())
                    .status(member.getStatus())
                    .build();
            data.add(saveMember);
            return saveMember;
        } else {
            data.removeIf(test -> Objects.equals(test.getId(),member.getId()));
            data.add(member);
            return member;
        }
    }

    @Override
    public Optional<Member> findByMobile(String mobile) {
        return data.stream().filter(test -> test.getPhone().equals(mobile)).findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return data.stream().filter(test -> test.getId().equals(id)).findAny();
    }

    @Override
    public void deleteMember(String phone) {

    }
}
