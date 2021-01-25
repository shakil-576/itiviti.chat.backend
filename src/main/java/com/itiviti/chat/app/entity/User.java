package com.itiviti.chat.app.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "USERS")
@Builder
public class User {
    @Id
    private String userName;
    private String name;
    private LocalDateTime lastLoginTime;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
