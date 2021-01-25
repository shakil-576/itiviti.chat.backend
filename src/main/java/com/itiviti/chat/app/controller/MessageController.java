package com.itiviti.chat.app.controller;

import com.itiviti.chat.app.entity.Message;
import com.itiviti.chat.app.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/chat/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("create")
    public Mono<Message> createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @GetMapping(value = "room/{id}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<Message> getMessagesByRoomId(@PathVariable("id") final String chatRoomId) {
        return messageService.getMessagesByRoomId(chatRoomId);
    }
}
