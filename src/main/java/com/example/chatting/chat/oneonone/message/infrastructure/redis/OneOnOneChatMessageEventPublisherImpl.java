package com.example.chatting.chat.oneonone.message.infrastructure.redis;

import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEvent;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEventPublisher;
import com.example.chatting.chat.oneonone.message.infrastructure.redis.enums.RedisKey;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneOnOneChatMessageEventPublisherImpl implements OneOnOneChatMessageEventPublisher {

    private final JsonConverter jsonConverter;
    private final RedissonClient redissonClient;

    public void publishOneOnOneChatMessageEvent(OneOnOneChatMessageEvent event){
        String message = jsonConverter.convertToJson(event);
        RTopic topic = redissonClient.getTopic(RedisKey.ONEONONE_CHAT_CHANNEL);
        topic.publish(message);
    }
}

