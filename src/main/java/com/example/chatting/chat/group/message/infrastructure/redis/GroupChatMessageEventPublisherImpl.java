package com.example.chatting.chat.group.message.infrastructure.redis;

import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEvent;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEventPublisher;
import com.example.chatting.chat.group.message.infrastructure.redis.enums.RedisKey;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupChatMessageEventPublisherImpl implements GroupChatMessageEventPublisher {

    private final JsonConverter jsonConverter;
    private final RedissonClient redissonClient;

    public void publishGroupChatMessageEvent(GroupChatMessageEvent event){
        String message = jsonConverter.convertToJson(event);
        RTopic topic = redissonClient.getTopic(RedisKey.GROUP_CHAT_CHANNEL);
        topic.publish(message);
    }
}

