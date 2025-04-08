package com.example.chatting.chat.oneonone.message.domain.event;

public interface ChatMessageEventProducer {
    void sendChatMessageEvent(ChatMessageEvent event);
}
