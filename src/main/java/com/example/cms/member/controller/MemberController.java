package com.example.cms.member.controller;


import com.example.cms.member.controller.port.MemberService;
import com.example.cms.member.controller.response.MemberCreateResponse;
import com.example.cms.member.controller.response.MemberResponse;
import com.example.cms.member.controller.response.MemberUpdateResponse;

import com.example.cms.member.domain.MemberCreate;
import com.example.cms.member.domain.MemberUpdate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Builder
@Tag(name = "MemberEntity", description = "회원 API")
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @Operation(summary = "회원 생성", description = "회원에 대한 생성을 진행합니다.")
    @PostMapping("/create")
    public ResponseEntity<MemberCreateResponse> createMember(@RequestBody @Valid MemberCreate create){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberCreateResponse
                        .from(memberService.save(create)));
    }

    @Operation(summary = "회원 조회", description = "회원에 대한 조회를 진행합니다.")
    @GetMapping("/membership/{phone}")
    public ResponseEntity<MemberResponse> findMembership(@PathVariable String phone){
        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberResponse
                        .from(memberService.findMembership(phone)));
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping("/{previousPhone}/update")
    public  ResponseEntity<MemberUpdateResponse> updateMember(@PathVariable String previousPhone,
                                                              @RequestBody @Valid MemberUpdate update){
        return ResponseEntity.ok()
                .body(MemberUpdateResponse
                        .from(memberService.memberUpdate(previousPhone,update)));
    }


    @Operation(summary = "회원 정보 블라인드", description = "회원 정보를 블라인드 합니다.")
    @PatchMapping("/{phone}/blind")
    public  void blindMember(@PathVariable String phone){
        memberService.blindMember(phone);
    }

}
