package com.itiviti.chat.app.service;

import com.itiviti.chat.app.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface MessageService {
    Mono<Message> createMessage(final Message message);

    Flux<Message> getMessagesByRoomId(final String chatRoomId);
}
