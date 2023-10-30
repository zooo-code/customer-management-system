package com.example.cms.member.domain;

import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Member {

    private final Long id;
    private final String phone;
    private final String name;
    private Integer membershipPoint;
    private final EMemberStatus status;

    private final Long createAt;
    private final Long modifiedAt;
    @Builder
    public Member(Long id, String phone, String name, Integer membershipPoint, EMemberStatus status,
                  Long createAt, Long modifiedAt) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public static Member from(MemberCreate memberCreate, ClockHolder clockHolder){
        Integer firstPoint = 0;
        Long createAtMillis = (clockHolder != null) ? clockHolder.millis() : System.currentTimeMillis();
        return Member.builder()
                .phone(memberCreate.getPhone())
                .name(memberCreate.getName())
                .membershipPoint(firstPoint)
                .status(EMemberStatus.OPEN)
                .createAt(createAtMillis)
                .build();
    }
    public Member update(MemberUpdate memberUpdate,ClockHolder clockHolder){
        Long modifiedAtMillis = (clockHolder != null) ? clockHolder.millis() : System.currentTimeMillis();
        return Member.builder()
                .id(id)
                .status(status)
                .membershipPoint(membershipPoint)
                .modifiedAt(modifiedAtMillis)
                .phone(memberUpdate.getPhone())
                .name(memberUpdate.getName())
                .build();
    }

    public void updatePoint(int remainPoint) {
        this.membershipPoint = remainPoint;
    }
}
