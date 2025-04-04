package com.example.chatting.member.service;

import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import com.example.chatting.member.domain.Member;
import com.example.chatting.member.repository.MemberRepository;
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

    public void createMember(String name, String email, String password){
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        if(memberOpt.isPresent()){
            throw new CustomException(CustomExceptionType.MEMBER_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(password);

        Member member = Member.of(name, email, encodedPassword);
        memberRepository.save(member);
    }

    public String login(String email, String password){
        Member member = memberRepository.findByEmail(email)
                                        .orElseThrow(() -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(CustomExceptionType.INVALID_PASSWORD);
        }

        return jwtProvider.generateToken(member.getEmail());
    }
}
