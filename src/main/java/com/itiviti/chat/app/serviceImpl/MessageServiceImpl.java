package com.itiviti.chat.app.serviceImpl;

import com.itiviti.chat.app.entity.Message;
import com.itiviti.chat.app.repository.CustomMessageRepository;
import com.itiviti.chat.app.repository.MessageRepository;
import com.itiviti.chat.app.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final CustomMessageRepository customMessageRepository;

    @Override
    public Mono<Message> createMessage(Message message) {
        message.setMessageId(String.valueOf(UUID.randomUUID()));
        message.setTimeStamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public Flux<Message> getMessagesByRoomId(String chatRoomId) {
        return customMessageRepository.getMessagesByRoomId(chatRoomId);
    }
}
