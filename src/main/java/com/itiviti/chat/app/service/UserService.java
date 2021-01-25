package com.itiviti.chat.app.service;

import com.itiviti.chat.app.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
    Mono<User> saveUserInfo(final User user);

    Mono<User> getUser(final String id);

    Mono<Boolean> isUserOnlineById(final String id);

    Mono<User> registerOnlineActivity(final String id);

    Flux<User> getAllUsers();
}
