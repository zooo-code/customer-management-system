package com.example.cms.member.controller.port;

import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;
import com.example.cms.member.domain.Member;
import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Member save(MemberCreate memberCreate);
    MemberResponse findMembership(String mobile);
    Member memberUpdate(String previousPhone, MemberUpdate memberUpdate);
    void findMemberList();
    MemberPageResponse memberListPaging(Pageable pageable);

}
