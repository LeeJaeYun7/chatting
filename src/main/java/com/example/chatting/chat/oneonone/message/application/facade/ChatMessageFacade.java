package com.example.chatting.chat.oneonone.message.application.facade;

import com.example.chatting.chat.oneonone.message.application.ChatMessageService;
import com.example.chatting.chat.oneonone.message.controller.dto.response.ChatMessageResponse;
import com.example.chatting.chat.oneonone.message.controller.dto.response.ChatMessageResponseList;
import com.example.chatting.chat.oneonone.message.domain.ChatMessage;
import com.example.chatting.chat.oneonone.message.domain.event.ChatMessageEvent;
import com.example.chatting.chat.oneonone.message.infrastructure.kafka.ChatMessageEventProducerImpl;
import com.example.chatting.chat.oneonone.room.application.OneOnOneChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatMessageFacade {

    private final OneOnOneChatRoomService oneOnOneChatRoomService;
    private final ChatMessageService chatMessageService;
    private final ChatMessageEventProducerImpl chatMessageEventProducerImpl;
    public void createChatMessage(long roomId, long senderId, String content){

        long receiverId = oneOnOneChatRoomService.getChatRoomReceiverId(roomId, senderId);
        LocalDateTime timestamp = chatMessageService.createChatMessage(roomId, senderId, receiverId, content);

        ChatMessageEvent chatMessageEvent = ChatMessageEvent.of(String.valueOf(roomId), String.valueOf(senderId), String.valueOf(receiverId), content, timestamp);
        chatMessageEventProducerImpl.sendChatMessageEvent(chatMessageEvent);
    }

    public ChatMessageResponseList getChatMessagesByRoomId(long roomId) {
        List<ChatMessageResponse> messages = chatMessageService.getChatMessagesByRoomId(roomId).stream()
                                                                    .sorted(Comparator.comparing(ChatMessage::getTimestamp))
                                                                    .map(chatMessage -> ChatMessageResponse.of(
                                                                            chatMessage.getSenderId(),
                                                                            chatMessage.getReceiverId(),
                                                                            chatMessage.getContent(),
                                                                            chatMessage.getTimestamp()))
                                                                   .toList();

        return ChatMessageResponseList.of(roomId, messages);
    }
}
