package com.example.chatting.chat.oneonone.message.domain.event;

public interface OneOnOneChatMessageEventPublisher {
    void publishOneOnOneChatMessageEvent(OneOnOneChatMessageEvent event);
}
