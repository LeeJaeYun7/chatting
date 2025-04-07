package com.example.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.chatting.chatmessage.infrastructure"
)
public class MongoConfig {
}