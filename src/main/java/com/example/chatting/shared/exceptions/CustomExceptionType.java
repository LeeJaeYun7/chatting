package com.example.chatting.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomExceptionType {
    ONE_ONE_CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 1:1 채팅방을 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 멤버를 찾을 수 없습니다."),
    MEMBER_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 이메일이 이미 존재합니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 잘못 되었습니다."),
    FRIEND_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 친구가 이미 존재합니다."),
    CHATROOM_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 채팅방이 이미 존재합니다."),
    GROUP_CHATROOM_PARTICIPANT_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "그룹 채팅방은 최대 100명까지 참여할 수 있습니다."); // 🔥 이 줄 추가


    private final HttpStatus httpStatus;
    private final String message;

    CustomExceptionType(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}