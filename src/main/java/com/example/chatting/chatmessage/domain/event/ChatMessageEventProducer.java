package com.example.chatting.chatmessage.domain.event;

public interface ChatMessageEventProducer {
    void sendChatMessageEvent(ChatMessageEvent event);
}
