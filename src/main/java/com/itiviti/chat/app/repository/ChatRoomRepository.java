package com.itiviti.chat.app.repository;

import com.itiviti.chat.app.entity.ChatRoom;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends ReactiveMongoRepository<ChatRoom, String> {

}
