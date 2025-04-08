package com.example.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {"com.example.chatting.chat.oneonone.message.infrastructure", "com.example.chatting.chat.group.message.infrastructure"}
)
public class MongoConfig {
}