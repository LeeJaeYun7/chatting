package com.example.chatting.config;

import com.example.chatting.chatmessage.infrastructure.ChatMessageRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "com.example.chatting",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = { ChatMessageRepository.class }
        )
)
public class JpaConfig {
}
