package com.example.chatting.shared.utils;

import java.util.UUID;

public class SnowFlakeGenerator {

    public static long createSnowFlake() {
        long timestamp = System.currentTimeMillis();
        long randomUUID = Math.abs(UUID.randomUUID().getMostSignificantBits());

        // Snowflake 형식에 맞는 64비트 값을 생성
        return (timestamp << 32) | (randomUUID & 0xFFFFFFFFL);
    }
}