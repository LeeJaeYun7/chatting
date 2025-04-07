package com.example.chatting.websocket.dao;

import com.example.chatting.websocket.dao.enums.RedisKey;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketIpDAO {

    private final RedissonClient redissonClient;

    public String getWebSocketIp(String memberId) {
        RMapCache<String, String> ipMap = redissonClient.getMapCache(RedisKey.WEBSOCKET_IP_KEY);
        return ipMap.get(memberId);
    }
}
