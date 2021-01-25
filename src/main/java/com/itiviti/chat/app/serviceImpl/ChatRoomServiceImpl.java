package com.itiviti.chat.app.serviceImpl;

import com.itiviti.chat.app.entity.ChatRoom;
import com.itiviti.chat.app.repository.ChatRoomRepository;
import com.itiviti.chat.app.repository.CustomChatRoomRepository;
import com.itiviti.chat.app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final CustomChatRoomRepository customChatRoomRepository;

    @Override
    public Mono<ChatRoom> createRoomIfNotExists(List<String> participantsIds) {
        return customChatRoomRepository.findChatRoom(participantsIds)
                .switchIfEmpty(createRoom(participantsIds));
    }

    private Mono<ChatRoom> createRoom(List<String> participantsIds) {
        final ChatRoom chatRoom = ChatRoom.builder()
                .chatRoomId(String.valueOf(UUID.randomUUID()))
                .participantsIds(participantsIds)
                .build();

        return chatRoomRepository.save(chatRoom);
    }
}
