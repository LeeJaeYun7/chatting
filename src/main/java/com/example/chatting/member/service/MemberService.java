package com.example.chatting.member.service;

import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import com.example.chatting.member.controller.dto.response.LoginResponse;
import com.example.chatting.member.controller.dto.response.MemberResponse;
import com.example.chatting.member.domain.Member;
import com.example.chatting.member.infrastructure.MemberRepository;
import com.example.chatting.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void createMember(String name, String email, String password, String makeStarId, String phoneNumber){
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        if(memberOpt.isPresent()){
            throw new CustomException(CustomExceptionType.MEMBER_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(password);

        Member member = Member.of(name, email, encodedPassword, makeStarId, phoneNumber);
        memberRepository.save(member);
    }

    public LoginResponse login(String email, String password){
        Member member = memberRepository.findByEmail(email)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(CustomExceptionType.INVALID_PASSWORD);
        }

        long memberId = member.getId();
        String jwtToken = jwtProvider.generateToken(member.getEmail());

        return LoginResponse.of(memberId, jwtToken);
    }

    public void validateMember(long memberId){
        memberRepository.findById(memberId)
                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));
    }

    public MemberResponse getMemberByServiceId(String serviceId){
        Member member = memberRepository.findByServiceId(serviceId)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));

        long memberId = member.getId();
        return MemberResponse.of(memberId);
    }
}
