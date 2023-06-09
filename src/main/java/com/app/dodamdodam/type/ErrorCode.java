package com.app.dodamdodam.type;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAILED("AUTH_001", "LOGIN_FAILED.", 401),
    AUTHENTICATION_FAILED("AUTH_002", "AUTHENTICATION_FAILED", 401),
    BOARD_NOT_FOUNT("NOT_FOUND_001", "BOARD_NOT_FOUND", 404),
    MEMBER_NOT_FOUND("NOT_FOUND_002", "MEMBER_NOT_FOUND", 404),
    CHAT_ROOM_NOT_FOUND("NOT_FOUND_003", "CHAT_ROOM_NOT_FOUND", 404);

    private final String code;
    private final String message;
    private final int status;

//    필드 초기화
    ErrorCode(final String code, final String message, final int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
