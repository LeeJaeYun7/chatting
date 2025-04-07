package com.example.chatting.chatmessage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ChatMessageResponseList {

    private long roomId;
    private List<ChatMessageResponse> messages;

    @Builder
    public ChatMessageResponseList(long roomId, List<ChatMessageResponse> messages){
        this.roomId = roomId;
        this.messages = messages;
    }

    public static ChatMessageResponseList of(long roomId, List<ChatMessageResponse> messages){
        return ChatMessageResponseList.builder()
                                      .roomId(roomId)
                                      .messages(messages)
                                      .build();
    }


}
