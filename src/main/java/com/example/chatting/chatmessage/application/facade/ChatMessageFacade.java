package com.example.chatting.chatmessage.application.facade;

import com.example.chatting.chatmessage.controller.dto.response.ChatMessageResponse;
import com.example.chatting.chatmessage.controller.dto.response.ChatMessageResponseList;
import com.example.chatting.chatmessage.application.ChatMessageService;
import com.example.chatting.chatmessage.domain.event.ChatMessageEvent;
import com.example.chatting.chatmessage.infrastructure.kafka.ChatMessageEventProducerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatMessageFacade {

    private final ChatMessageService chatMessageService;
    private final ChatMessageEventProducerImpl chatMessageEventProducerImpl;
    public void createChatMessage(long roomId, long senderId, long receiverId, String content){
        LocalDateTime timestamp = chatMessageService.createChatMessage(roomId, senderId, receiverId, content);

        ChatMessageEvent chatMessageEvent = ChatMessageEvent.of(String.valueOf(roomId), String.valueOf(senderId), String.valueOf(receiverId), content, timestamp);
        chatMessageEventProducerImpl.sendChatMessageEvent(chatMessageEvent);
    }

    public ChatMessageResponseList getChatMessagesByRoomId(long roomId) {
        List<ChatMessageResponse> messages = chatMessageService.getChatMessagesByRoomId(roomId).stream()
                                                                    .sorted(Comparator.comparing(chatMessage -> chatMessage.getTimestamp()))
                                                                    .map(chatMessage -> ChatMessageResponse.of(
                                                                            chatMessage.getSenderId(),
                                                                            chatMessage.getReceiverId(),
                                                                            chatMessage.getContent(),
                                                                            chatMessage.getTimestamp()))
                                                                   .toList();

        return ChatMessageResponseList.of(roomId, messages);
    }
}
