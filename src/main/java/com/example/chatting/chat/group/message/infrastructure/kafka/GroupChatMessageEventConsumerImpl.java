package com.example.chatting.chat.group.message.infrastructure.kafka;

import com.example.chatting.chat.group.message.application.facade.GroupChatMessageFacade;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEventConsumer;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageServerEvent;
import com.example.chatting.chat.group.message.infrastructure.kafka.enums.GroupChatMessageTopics;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupChatMessageEventConsumerImpl implements GroupChatMessageEventConsumer {

    private final JsonConverter jsonConverter;
    private final GroupChatMessageFacade groupChatMessageFacade;

    @KafkaListener(
            topics = GroupChatMessageTopics.CHAT_MESSAGE_TOPIC,
            groupId = "group-chat-message-group"
    )
    public void receiveGroupChatMessage(String message) {
        GroupChatMessageServerEvent event = jsonConverter.convertFromJson(message, GroupChatMessageServerEvent.class);

        long roomId = Long.parseLong(event.getRoomId());
        long senderId = Long.parseLong(event.getSenderId());
        String content = event.getContent();

        groupChatMessageFacade.createGroupChatMessage(roomId, senderId, content);
    }
}
