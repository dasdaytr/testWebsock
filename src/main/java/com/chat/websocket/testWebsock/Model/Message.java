package com.chat.websocket.testWebsock.Model;

import lombok.Data;

@Data
public class Message {
    private int idChatRoom;
    private String fromUser;
    private String message;
    private int idMessage;
}
