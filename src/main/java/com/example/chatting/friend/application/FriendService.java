package com.example.chatting.friend.application;

import com.example.chatting.shared.exceptions.CustomException;
import com.example.chatting.shared.exceptions.CustomExceptionType;
import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.infrastructure.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional
    public void createFriend(long memberId, long friendId){
        Friend friend = Friend.of(memberId, friendId);
        friendRepository.save(friend);
    }

    public List<Friend> readFriendList(long memberId){
        return friendRepository.findByMemberId(memberId);
    }

    public void validateFriend(long memberId, long friendId){
        Optional<Friend> friend = friendRepository.findByMemberIdAndFriendId(memberId, friendId);

        if(friend.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
