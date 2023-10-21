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
    @Column(length = 11,nullable = false)
    private String mobile;
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "membership_point",nullable = false)
    private Integer membershipPoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private EMemberStatus status;




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
        memberEntity.id = member.getId();
        memberEntity.mobile = member.getPhone();
        memberEntity.name = member.getName();
        memberEntity.status = member.getStatus();

        memberEntity.membershipPoint = member.getMembershipPoint();
        return memberEntity;
    }

    public Member toModel(){
        return Member.builder()
                .id(id)
                .phone(mobile)
                .name(name)
                .membershipPoint(membershipPoint)
                .status(status)
                .build();
    }
}
