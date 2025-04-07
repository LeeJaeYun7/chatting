package com.example.chatting.member.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import com.example.chatting.shared.utils.SnowFlakeGenerator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "service_id", unique = true)
    private String serviceId;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Builder
    public Member(long memberId, String name, String email, String password, String serviceId, String phoneNumber){
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.serviceId = serviceId;
        this.phoneNumber = phoneNumber;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static Member of(String name, String email, String password, String serviceId, String phoneNumber){
        return Member.builder()
                     .memberId(SnowFlakeGenerator.createSnowFlake())
                     .name(name)
                     .email(email)
                     .password(password)
                     .serviceId(serviceId)
                     .phoneNumber(phoneNumber)
                     .build();
    }
}
