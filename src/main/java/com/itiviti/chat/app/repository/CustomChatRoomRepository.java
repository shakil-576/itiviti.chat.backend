package com.itiviti.chat.app.repository;

import com.itiviti.chat.app.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomChatRoomRepository {

    private final ReactiveMongoTemplate reactiveTemplate;

    public Mono<ChatRoom> findChatRoom(final List<String> participantsIds) {
        final Query query = new Query(Criteria.where("participantsIds").all(participantsIds).size(2));

        return reactiveTemplate.findOne(query, ChatRoom.class);
    }
}
