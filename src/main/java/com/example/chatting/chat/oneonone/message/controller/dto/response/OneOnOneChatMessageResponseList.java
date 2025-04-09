package com.example.chatting.chat.oneonone.message.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OneOnOneChatMessageResponseList {

    private long roomId;
    private List<OneOnOneChatMessageResponse> messages;

    @Builder
    public OneOnOneChatMessageResponseList(long roomId, List<OneOnOneChatMessageResponse> messages){
        this.roomId = roomId;
        this.messages = messages;
    }

    public static OneOnOneChatMessageResponseList of(long roomId, List<OneOnOneChatMessageResponse> messages){
        return OneOnOneChatMessageResponseList.builder()
                                      .roomId(roomId)
                                      .messages(messages)
                                      .build();
    }


}
