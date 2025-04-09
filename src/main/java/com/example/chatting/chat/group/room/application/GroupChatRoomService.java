package com.example.chatting.chat.group.room.application;

import com.example.chatting.chat.group.room.domain.GroupChatRoom;
import com.example.chatting.chat.group.room.infrastructure.GroupChatRoomRepository;
import com.example.chatting.shared.utils.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupChatRoomService {

    private final GroupChatRoomRepository groupChatRoomRepository;
    private final SnowflakeIdGenerator idGenerator;
    public long createGroupChatRoom(long creatorId, String name){
        long roomId = idGenerator.nextId();

        GroupChatRoom groupChatRoom = GroupChatRoom.of(roomId, creatorId, name);
        GroupChatRoom savedGroupChatRoom = groupChatRoomRepository.save(groupChatRoom);
        return savedGroupChatRoom.getRoomId();
    }
}
