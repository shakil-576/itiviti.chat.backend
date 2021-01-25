package com.itiviti.chat.app.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "CHAT_ROOMS")
public class ChatRoom {
    @Id
    private String chatRoomId;
    private List<String> participantsIds;

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoomId='" + chatRoomId + '\'' +
                ", participantsIds=" + participantsIds +
                '}';
    }
}
