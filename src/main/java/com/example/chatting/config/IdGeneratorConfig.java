package com.example.chatting.config;

import com.example.chatting.shared.utils.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfig {

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator() {
        return new SnowflakeIdGenerator(1L); // 필요시 worker/datacenter ID 설정
    }
}
