package com.example.chatting.member.controller;

import com.example.chatting.member.controller.dto.request.MemberCreateRequest;
import com.example.chatting.member.controller.dto.request.MemberLoginRequest;
import com.example.chatting.member.controller.dto.response.LoginResponse;
import com.example.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member/create")
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        memberService.createMember(name, email, password);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/v1/member/login")
    public ResponseEntity<LoginResponse> login(@RequestBody MemberLoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();

        LoginResponse loginResponse  = memberService.login(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
