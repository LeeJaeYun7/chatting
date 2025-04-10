package com.example.chatting.chat.group.message.domain.event;

public interface GroupChatMessageEventPublisher {
    void publishGroupChatMessageEvent(GroupChatMessageEvent event);
}
