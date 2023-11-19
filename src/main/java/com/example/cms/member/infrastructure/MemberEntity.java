package com.example.cms.member.infrastructure;

import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.domain.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "member", indexes = @Index(name ="idx__mobile",columnList = "mobile", unique = true))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name = "mobile",length = 11,nullable = false)
    private String mobile;
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "membership_point",nullable = false)
    private Integer membershipPoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private EMemberStatus status;

    @Column(name = "create_at")
    private Long createAt;

    @Column(name = "modify_at")
    private Long modifyAt;

    @Builder
    public MemberEntity(Long id, String mobile, String name, Integer membershipPoint, EMemberStatus status, Long createAt) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.membershipPoint = membershipPoint;
        this.status = status;
        this.createAt = createAt;
    }



    public static MemberEntity from(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = member.getId();
        memberEntity.mobile = member.getPhone();
        memberEntity.name = member.getName();
        memberEntity.status = member.getStatus();
        memberEntity.createAt = member.getCreateAt();
        memberEntity.membershipPoint = member.getMembershipPoint();
        memberEntity.modifyAt = member.getModifiedAt();
        return memberEntity;
    }

    public Member toModel(){
        return Member.builder()
                .id(id)
                .phone(mobile)
                .name(name)
                .membershipPoint(membershipPoint)
                .createAt(createAt)
                .status(status)
                .build();
    }
}
