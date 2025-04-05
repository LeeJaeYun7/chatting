package com.example.chatting.member.service;

import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import com.example.chatting.member.controller.dto.response.LoginResponse;
import com.example.chatting.member.controller.dto.response.MemberResponse;
import com.example.chatting.member.domain.Member;
import com.example.chatting.member.infrastructure.MemberRepository;
import com.example.chatting.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signupMember(String name, String email, String password, String serviceId, String phoneNumber){
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        if(memberOpt.isPresent()){
            throw new CustomException(CustomExceptionType.MEMBER_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(password);

        Member member = Member.of(name, email, encodedPassword, serviceId, phoneNumber);
        memberRepository.save(member);
    }

    public LoginResponse login(String email, String password){
        Member member = memberRepository.findByEmail(email)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(CustomExceptionType.INVALID_PASSWORD);
        }

        String memberId = member.getUuid();
        String jwtToken = jwtProvider.generateToken(memberId);

        return LoginResponse.of(jwtToken);
    }

    public void validateMember(String uuid){
        memberRepository.findByUuid(uuid)
                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));
    }

    public String getMemberNameByUuid(String uuid){
        Member member = memberRepository.findByUuid(uuid)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));
        return member.getName();
    }

    public MemberResponse getMemberByServiceId(String serviceId){
        Member member = memberRepository.findByServiceId(serviceId)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));

        String uuid = member.getUuid();
        return MemberResponse.of(uuid);
    }
}
