package com.itiviti.chat.app.serviceImpl;

import com.itiviti.chat.app.entity.User;
import com.itiviti.chat.app.repository.CustomUserRepository;
import com.itiviti.chat.app.repository.UserRepository;
import com.itiviti.chat.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;

    @Override
    public Mono<User> saveUserInfo(final User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> getUser(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<Boolean> isUserOnlineById(final String id) {
        return customUserRepository.isUserOnlineById(id);
    }

    @Override
    public Mono<User> registerOnlineActivity(final String id) {
        return customUserRepository.registerOnlineActivity(id);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}
