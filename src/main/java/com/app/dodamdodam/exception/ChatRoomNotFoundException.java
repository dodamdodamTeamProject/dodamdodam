package com.app.dodamdodam.exception;

import com.app.dodamdodam.type.ErrorCode;

public class ChatRoomNotFoundException extends RuntimeException {

    public ChatRoomNotFoundException() {
        super(ErrorCode.CHAT_ROOM_NOT_FOUND.getMessage());
    }

    public ChatRoomNotFoundException(String message) {
        super(message);
    }
}
