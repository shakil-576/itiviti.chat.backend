package com.itiviti.chat.app.runner;

import com.itiviti.chat.app.entity.Message;
import com.itiviti.chat.app.entity.User;
import com.itiviti.chat.app.service.ChatRoomService;
import com.itiviti.chat.app.service.MessageService;
import com.itiviti.chat.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements CommandLineRunner {
    private final UserService userService;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final ReactiveMongoTemplate reactiveTemplate;

    @Override
    public void run(String... args) throws Exception {
//        insertDummyData();
//        Stream.of(createUser("shakil")
//        ).forEach(user -> {
//            userService.saveUserInfo(user).subscribe(System.out::println);
//        });
    }

    private void insertDummyData() {
        log.info("Dummy Data insertion starts !!!");

        Stream.of(createUser("shakil"),
                createUser("salma"),
                createUser("najma"),
                createUser("shifa"),
                createUser("simran")
        ).forEach(user -> {
            userService.saveUserInfo(user).subscribe(System.out::println);
        });

        Stream.of((List.of("shakil", "shifa")))
                .map(chatRoom -> chatRoomService.createRoomIfNotExists(chatRoom))
                .forEach(chatRoomMono -> chatRoomMono.subscribe(chatRoom -> {
                    System.out.println(chatRoom);
                    messageService.createMessage(createMessage(chatRoom.getChatRoomId(),
                            "Hello from shakil to shifa",
                            "shakil",
                            "shifa")).subscribe(System.out::println);
                }));

        log.info("Insert Complete");
    }

    private User createUser(final String userName) {
        return User.builder()
                .userName(userName)
                .name(userName)
                .lastLoginTime(LocalDateTime.now())
                .build();
    }

    private Message createMessage(final String chatRoomId, final String content, final String sentBy, final String sentTo) {
        return Message.builder()
                .chatRoomId(chatRoomId)
                .content(content)
                .sentBy(sentBy)
                .sentTo(sentTo)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}



