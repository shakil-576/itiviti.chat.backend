package com.itiviti.chat.app.repository;

import com.itiviti.chat.app.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class CustomMessageRepository {

    private final ReactiveMongoTemplate reactiveTemplate;

    public Flux<Message> getMessagesByRoomId(final String chatRoomId) {
        final Query query = new Query(Criteria.where("chatRoomId").is(chatRoomId));

        final Flux<Message> allMessagesByRoomId = reactiveTemplate.find(query, Message.class, "MESSAGES");

        final Flux<Message> changeStreamMessages = reactiveTemplate.changeStream(Message.class)
                .watchCollection("MESSAGES")
                .listen()
                .map(ChangeStreamEvent::getBody)
                .filter(message -> chatRoomId.equals(message.getChatRoomId()));

        return Flux.mergeSequential(allMessagesByRoomId, changeStreamMessages);
    }
}
