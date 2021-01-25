package com.itiviti.chat.app.controller;

import com.itiviti.chat.app.constant.AppConstants;
import com.itiviti.chat.app.entity.User;
import com.itiviti.chat.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/chat/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("create")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.saveUserInfo(user);
    }

    @PutMapping("create/temp")
    public Mono<User> createUserTemp(@RequestBody User user) {
        user.setLastLoginTime(LocalDateTime.now());
        user.setName(user.getName());
        return userService.saveUserInfo(user);
    }

    @GetMapping(value = "{id}")
    public Mono<User> getUser(@PathVariable("id") final String id) {
        return userService.getUser(id);
    }

    @GetMapping(value = "list")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "online/{id}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<Boolean> isUserOnlineById(@PathVariable("id") final String id) {
        return Flux.interval(Duration.ofSeconds(AppConstants.USER_LOGIN_STATUS_CHECK_RATE))
                .switchMap(aLong -> userService.isUserOnlineById(id));
    }

    @GetMapping(value = "registerOnlineActivity/{id}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<User> registerOnlineActivity(@PathVariable("id") final String id) {
        return Flux.interval(Duration.ofSeconds(AppConstants.REGISTER_ONLINE_ACTIVITY_RATE))
                .switchMap(aLong -> userService.registerOnlineActivity(id));
    }
}
