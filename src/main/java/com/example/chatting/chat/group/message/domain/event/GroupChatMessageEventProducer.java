package com.example.chatting.chat.group.message.domain.event;

public interface GroupChatMessageEventProducer {
    void sendGroupChatMessageEvent(GroupChatMessageEvent event);
}
