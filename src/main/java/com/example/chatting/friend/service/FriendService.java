package com.example.chatting.friend.service;

import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.infrastructure.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public void createFriend(long memberId, long friendMemberId){
        Friend friend = Friend.of(memberId, friendMemberId);
        friendRepository.save(friend);
    }

    public List<Friend> readFriendList(long memberId){
        return friendRepository.findByMemberId(memberId);
    }

    public void validateFriend(long memberId, long friendMemberId){
        Optional<Friend> friend = friendRepository.findByMemberIdAndFriendMemberId(memberId, friendMemberId);

        if(friend.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
