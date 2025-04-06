package com.example.chatting.chatroom.oneonone.application;

import com.example.chatting.chatroom.oneonone.domain.OneOnOneChatRoom;
import com.example.chatting.chatroom.oneonone.infrastructure.OneOnOneChatRoomRepository;
import com.example.chatting.shared.exceptions.CustomException;
import com.example.chatting.shared.exceptions.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneOnOneChatRoomService {

    private final OneOnOneChatRoomRepository oneOnOneChatRoomRepository;

    public String createOneOnOneChatRoom(String uuid1, String uuid2){
        OneOnOneChatRoom oneOnOneChatRoom = OneOnOneChatRoom.of(uuid1, uuid2);
        OneOnOneChatRoom savedOneOnOneChatRoom = oneOnOneChatRoomRepository.save(oneOnOneChatRoom);
        return savedOneOnOneChatRoom.getRoomUuid();
    }

    public List<OneOnOneChatRoom> readOneOnOneChatRooms(String memberUuid){
        return oneOnOneChatRoomRepository.findByMemberUuid(memberUuid);
    }

    public void validateOneOnOneChatRoom(String memberUuid1, String memberUuid2){
        Optional<OneOnOneChatRoom> oneOnOneChatRoom = oneOnOneChatRoomRepository.findByMemberUuids(memberUuid1, memberUuid2);

        if(oneOnOneChatRoom.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
