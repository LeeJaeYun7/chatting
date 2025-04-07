package com.example.chatting.chatmessage.application.facade;

import com.example.chatting.chatmessage.controller.dto.response.ChatMessageResponse;
import com.example.chatting.chatmessage.controller.dto.response.ChatMessageResponseList;
import com.example.chatting.chatmessage.application.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatMessageFacade {

    private final ChatMessageService chatMessageService;

    public void createChatMessage(long roomId, long senderId, long receiverId, String content){
        chatMessageService.createChatMessage(roomId, senderId, receiverId, content);
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
