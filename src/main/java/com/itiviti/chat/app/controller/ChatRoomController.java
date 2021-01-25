package com.itiviti.chat.app.controller;

import com.itiviti.chat.app.entity.ChatRoom;
import com.itiviti.chat.app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/chat/")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PutMapping("/createRoom")
    public Mono<ChatRoom> createRoom(@RequestBody final List<String> participantsIds) {
        return chatRoomService.createRoomIfNotExists(participantsIds);
    }
}
