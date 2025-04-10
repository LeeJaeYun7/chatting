package com.example.chatting.chat.oneonone.message.infrastructure.kafka;

import com.example.chatting.chat.oneonone.message.application.facade.OneOnOneChatMessageFacade;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEvent;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEventConsumer;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageServerEvent;
import com.example.chatting.chat.oneonone.message.infrastructure.kafka.enums.OneOnOneChatMessageTopics;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneOnOneChatMessageEventConsumerImpl implements OneOnOneChatMessageEventConsumer {

    private final JsonConverter jsonConverter;
    private final OneOnOneChatMessageFacade oneOnOneChatMessageFacade;

    @KafkaListener(
            topics = OneOnOneChatMessageTopics.CHAT_MESSAGE_TOPIC,
            groupId = "one-on-one-chat-message-group"
    )
    public void receiveOneOnOneChatMessage(String message) {
        OneOnOneChatMessageServerEvent event = jsonConverter.convertFromJson(message, OneOnOneChatMessageServerEvent.class);

        long roomId = Long.parseLong(event.getRoomId());
        long senderId = Long.parseLong(event.getSenderId());
        String content = event.getContent();

        oneOnOneChatMessageFacade.createOneOnOneChatMessage(roomId, senderId, content);
    }
}
