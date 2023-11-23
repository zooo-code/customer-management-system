package com.example.cms.member.controller.port;

import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;

public interface MemberService {

    Member save(MemberCreate memberCreate);
    Member findMembership(String mobile);
    Member memberUpdate(String previousPhone, MemberUpdate memberUpdate);

    void blindMember(String phone);

}
