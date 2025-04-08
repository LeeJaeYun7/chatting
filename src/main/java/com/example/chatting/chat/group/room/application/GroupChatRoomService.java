package com.example.chatting.chat.group.room.application;

import com.example.chatting.chat.group.room.domain.GroupChatRoom;
import com.example.chatting.chat.group.room.infrastructure.GroupChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupChatRoomService {

    private final GroupChatRoomRepository groupChatRoomRepository;

    public long createGroupChatRoom(long creatorId, String name){
        GroupChatRoom groupChatRoom = GroupChatRoom.of(creatorId, name);
        GroupChatRoom savedGroupChatRoom = groupChatRoomRepository.save(groupChatRoom);
        return savedGroupChatRoom.getRoomId();
    }
}
