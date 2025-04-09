package com.example.chatting.member.infrastructure.redis;

import com.example.chatting.member.domain.event.MemberStatusNotificationEvent;
import com.example.chatting.member.domain.event.MemberStatusNotificationEventPublisher;
import com.example.chatting.member.infrastructure.redis.enums.MemberStatusEventChannels;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStatusNotificationEventPublisherImpl implements MemberStatusNotificationEventPublisher {

    private final JsonConverter jsonConverter;
    private final RedissonClient redissonClient;

    public void publishMemberStatusNotificationEvent(MemberStatusNotificationEvent event){
        String message = jsonConverter.convertToJson(event);
        RTopic topic = redissonClient.getTopic(MemberStatusEventChannels.MEMBER_STATUS_CHANNEL);
        topic.publish(message);
    }
}
