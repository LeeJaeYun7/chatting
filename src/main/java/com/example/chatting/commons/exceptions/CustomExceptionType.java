package com.example.chatting.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomExceptionType {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 멤버를 찾을 수 없습니다."),
    MEMBER_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 이메일이 이미 존재합니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 잘못 되었습니다."),
    FRIEND_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 친구가 이미 존재합니다."),
    CHATROOM_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 채팅방이 이미 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CustomExceptionType(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}