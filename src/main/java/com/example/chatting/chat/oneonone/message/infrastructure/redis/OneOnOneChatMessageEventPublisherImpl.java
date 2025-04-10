package com.example.chatting.chat.oneonone.message.infrastructure.redis;

import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEvent;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEventPublisher;
import com.example.chatting.chat.oneonone.message.infrastructure.redis.enums.RedisKey;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneOnOneChatMessageEventPublisherImpl implements OneOnOneChatMessageEventPublisher {

    private final JsonConverter jsonConverter;
    private final RedissonClient redissonClient;
    private final RetryTemplate retryTemplate;
    @Override
    public void publishOneOnOneChatMessageEvent(OneOnOneChatMessageEvent event){
        String message = jsonConverter.convertToJson(event);
        RTopic topic = redissonClient.getTopic(RedisKey.ONEONONE_CHAT_CHANNEL);

        retryTemplate.execute(context -> {
            try {
                topic.publish(message);  // 메시지 발행
            } catch (RedisConnectionException e) {
                // Redis 연결 오류 시 로그를 남기고 재시도
                // 예외 처리 로직 추가 가능
                throw e;
            }
            return null;
        });
    }
}

