package com.itiviti.chat.app.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "MESSAGES")
public class Message {
    @Id
    private String messageId;
    private String chatRoomId;
    private String content;
    private String sentBy;
    private String sentTo;
    private LocalDateTime timeStamp;

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", content='" + content + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", sentTo='" + sentTo + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
