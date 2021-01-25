package com.itiviti.chat.app.service;

import com.itiviti.chat.app.entity.ChatRoom;
import com.itiviti.chat.app.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface ChatRoomService {
    Mono<ChatRoom> createRoomIfNotExists(final List<String> participantsIds);
}
