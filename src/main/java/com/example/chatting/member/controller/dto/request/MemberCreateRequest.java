package com.example.chatting.member.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberCreateRequest {
    private String name;
    private String email;
    private String password;
    private String serviceId;
    private String phoneNumber;
}
