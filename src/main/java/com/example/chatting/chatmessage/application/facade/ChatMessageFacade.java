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

    public void createChatMessage(String roomUuid, String senderUuid, String receiverUuid, String content){
        chatMessageService.createChatMessage(roomUuid, senderUuid, receiverUuid, content);
    }

    public ChatMessageResponseList getChatMessagesByRoomUuid(String roomUuid) {
        List<ChatMessageResponse> messages = chatMessageService.getChatMessagesByRoomUuid(roomUuid).stream()
                                                                    .sorted(Comparator.comparing(chatMessage -> chatMessage.getTimestamp()))
                                                                    .map(chatMessage -> ChatMessageResponse.of(
                                                                            chatMessage.getSenderUuid(),
                                                                            chatMessage.getReceiverUuid(),
                                                                            chatMessage.getContent(),
                                                                            chatMessage.getTimestamp()))
                                                                   .toList();

        return ChatMessageResponseList.of(roomUuid, messages);
    }
}
