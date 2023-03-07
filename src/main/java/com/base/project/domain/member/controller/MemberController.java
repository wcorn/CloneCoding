package com.base.project.domain.member.controller;

import com.base.project.domain.member.dto.request.LogInRequstDto;
import com.base.project.domain.member.dto.request.SignUpRequestDto;
import com.base.project.domain.member.dto.response.LogInResponseDto;
import com.base.project.domain.member.service.MemberService;

import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignUpRequestDto request) {
        memberService.validationDuplicateEmail(request.getEmail());
        memberService.userSignUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.USER_SIGNUP));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LogInResponseDto>> logIn(@RequestBody LogInRequstDto request) {
        LogInResponseDto logInResponseDto = memberService.userLogIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(ResponseCode.USER_LOGIN, logInResponseDto));
    }
}