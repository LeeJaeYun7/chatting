package com.example.chatting.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUtils {

    // SecurityContext에서 memberUuid를 추출하는 메서드
    public static String getMemberUuid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();  // memberUuid가 username에 저장되어 있음
    }
}
