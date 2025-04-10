package com.example.chatting.chat.group.message.infrastructure.redis;

import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEvent;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEventPublisher;
import com.example.chatting.chat.group.message.infrastructure.redis.enums.RedisKey;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupChatMessageEventPublisherImpl implements GroupChatMessageEventPublisher {

    private final JsonConverter jsonConverter;
    private final RedissonClient redissonClient;
    private final RetryTemplate retryTemplate;

    @Override
    public void publishGroupChatMessageEvent(GroupChatMessageEvent event){
        String message = jsonConverter.convertToJson(event);
        RTopic topic = redissonClient.getTopic(RedisKey.GROUP_CHAT_CHANNEL);

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

