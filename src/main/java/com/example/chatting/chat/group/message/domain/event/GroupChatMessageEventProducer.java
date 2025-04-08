package com.example.chatting.chat.group.message.domain.event;

import java.util.List;

public interface GroupChatMessageEventProducer {
    void sendGroupChatMessageEvent(GroupChatMessageEvent event);
}
