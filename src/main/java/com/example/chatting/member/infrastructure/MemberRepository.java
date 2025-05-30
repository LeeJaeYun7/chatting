package com.example.chatting.member.infrastructure;

import com.example.chatting.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByServiceId(String serviceId);

    Optional<Member> findByMemberId(long memberId);
}
