package com.example.chatting.member.domain.event;

public interface MemberStatusNotificationEventPublisher {
    void publishMemberStatusNotificationEvent(MemberStatusNotificationEvent event);
}
