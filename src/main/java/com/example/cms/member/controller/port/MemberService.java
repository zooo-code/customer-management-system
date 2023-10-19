package com.example.cms.member.controller.port;

import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;
import com.example.cms.member.domain.MemberCreate;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    MemberCreateResponse save(MemberCreate memberCreate);
    MemberResponse findMembership(String mobile);
    MemberUpdateResponse memberUpdate(String previousPhone, MemberUpdateRequest request);
    void findMemberList();
    MemberPageResponse memberListPaging(Pageable pageable);

}
