package com.example.chatting.chat.oneonone.room.application;

import com.example.chatting.chat.oneonone.room.domain.OneOnOneChatRoom;
import com.example.chatting.chat.oneonone.room.infrastructure.OneOnOneChatRoomRepository;
import com.example.chatting.shared.exceptions.CustomException;
import com.example.chatting.shared.exceptions.CustomExceptionType;
import com.example.chatting.shared.utils.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneOnOneChatRoomService {

    private final OneOnOneChatRoomRepository oneOnOneChatRoomRepository;
    private final SnowflakeIdGenerator idGenerator;
    @Transactional
    public long createOneOnOneChatRoom(long memberId1, long memberId2){
        long roomId = idGenerator.nextId();

        OneOnOneChatRoom oneOnOneChatRoom = OneOnOneChatRoom.of(roomId, memberId1, memberId2);
        OneOnOneChatRoom savedOneOnOneChatRoom = oneOnOneChatRoomRepository.save(oneOnOneChatRoom);
        return savedOneOnOneChatRoom.getRoomId();
    }

    public List<OneOnOneChatRoom> readOneOnOneChatRooms(long memberId){
        return oneOnOneChatRoomRepository.findByMemberId(memberId);
    }

    public void validateOneOnOneChatRoom(long memberId1, long memberId2){
        Optional<OneOnOneChatRoom> oneOnOneChatRoom = oneOnOneChatRoomRepository.findByMemberIds(memberId1, memberId2);

        if(oneOnOneChatRoom.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }

    public long getChatRoomReceiverId(long roomId, long senderId){
        OneOnOneChatRoom oneOnOneChatRoom = oneOnOneChatRoomRepository.findByRoomId(roomId)
                                                                      .orElseThrow(() -> new CustomException(CustomExceptionType.ONE_ONE_CHATROOM_NOT_FOUND));
        long memberId1 = oneOnOneChatRoom.getMemberId1();
        long memberId2 = oneOnOneChatRoom.getMemberId2();

        return memberId1 == senderId? memberId2 : memberId1;
    }
}
