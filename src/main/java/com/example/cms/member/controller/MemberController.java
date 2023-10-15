package com.example.cms.member.controller;


import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.controller.request.MemberUpdateRequest;
import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberPageResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;

import com.example.cms.member.service.MemberServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@Tag(name = "MemberEntity", description = "회원 API")
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }
    @Operation(summary = "회원 생성", description = "회원에 대한 생성을 진행합니다.")
    @PostMapping("/create")
    public MemberCreateResponse createMember(@RequestBody @Valid MemberCreateRequest request){
        return memberServiceImpl.save(request);
    }

    @Operation(summary = "회원 조회", description = "회원에 대한 조회를 진행합니다.")
    @GetMapping("/membership/{phone}")
    public MemberResponse findMembership(@PathVariable String phone){
        return memberServiceImpl.findMembership(phone);
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping("/{previousPhone}/update")
    public MemberUpdateResponse updateMember(@PathVariable String previousPhone, @RequestBody @Valid MemberUpdateRequest request){
        return memberServiceImpl.memberUpdate(previousPhone,request);
    }


    @GetMapping("/memberList")
    public MemberPageResponse memberListPaging(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                               @RequestParam int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        memberServiceImpl.memberListPaging(pageRequest);

        return new MemberPageResponse();
    }

}
