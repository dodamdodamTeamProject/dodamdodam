package com.app.dodamdodam.domain.chat;

import com.app.dodamdodam.type.MessageType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatMessageDTO {

    private MessageType type; //메시지 타입
    private String roomId;// 방 번호
    private String sender;//채팅을 보낸 사람
    private String message;// 메세지
    private String time; // 채팅 발송 시간

    @Builder
    public ChatMessageDTO(MessageType type, String roomId, String sender, String message, String time) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }
}