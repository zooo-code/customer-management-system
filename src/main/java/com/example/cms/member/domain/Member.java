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
        final Integer FIRST_POINT = 0;
        return Member.builder()
                .phone(memberCreate.getPhone())
                .name(memberCreate.getName())
                .membershipPoint(FIRST_POINT)
                .status(EMemberStatus.OPEN)
                .createAt(clockHolder.millis())
                .build();
    }
    public Member update(MemberUpdate memberUpdate,ClockHolder clockHolder){
        if (memberUpdate.getName()== null){
            return Member.builder()
                    .id(id)
                    .status(memberUpdate.getStatus())
                    .membershipPoint(membershipPoint)
                    .modifiedAt(clockHolder.millis())
                    .phone(memberUpdate.getPhone())
                    .name(name)
                    .build();
        }else if(memberUpdate.getPhone() == null) {
            return Member.builder()
                    .id(id)
                    .status(memberUpdate.getStatus())
                    .membershipPoint(membershipPoint)
                    .modifiedAt(clockHolder.millis())
                    .phone(phone)
                    .name(memberUpdate.getName())
                    .build();
        }
        return Member.builder()
                .id(id)
                .status(memberUpdate.getStatus())
                .membershipPoint(membershipPoint)
                .modifiedAt(clockHolder.millis())
                .phone(memberUpdate.getPhone())
                .name(memberUpdate.getName())
                .build();
    }



    public void updatePoint(Integer remainPoint) {
        this.membershipPoint = remainPoint;
    }
}
