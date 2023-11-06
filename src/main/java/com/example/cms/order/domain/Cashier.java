package com.example.cms.order.domain;

import com.example.cms.member.domain.Member;

public class Cashier {


    public Member calculate(Member member, Order order){
        int memberPoint = order.getMember().getMembershipPoint();
        int payAmount = order.getOrdersPrice();
        if (memberPoint < payAmount) {
            throw new IllegalStateException("포인트가 부족합니다.");
        }
        int remainPoint = memberPoint - payAmount;
        //남은 포인트 set
        member.updatePoint(remainPoint);
        return member;
    }
}
