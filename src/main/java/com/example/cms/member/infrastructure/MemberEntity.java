package com.example.cms.member.infrastructure;

import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;
import com.example.cms.utils.entity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(length = 11)
    private String mobile;

    private String name;

    @Column(name = "membership_point")
    private Integer membershipPoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EMemberStatus status;

    //첫 회원 가입 포인트
    Integer firstJoinPoint = 10000;

    @Builder
    public MemberEntity(Long id, String mobile, String name, Integer membershipPoint, EMemberStatus status) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
    }
    public static MemberEntity from(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.mobile = member.getMobile();
        memberEntity.name = member.getName();
        memberEntity.status = member.getStatus();
        memberEntity.firstJoinPoint = member.getFirstJoinPoint();
        return memberEntity;
    }

    public Member toModel(){
        return Member.builder()
                .id(id)
                .mobile(mobile)
                .name(name)
                .membershipPoint(membershipPoint)
                .status(status)
                .build();
    }
}
