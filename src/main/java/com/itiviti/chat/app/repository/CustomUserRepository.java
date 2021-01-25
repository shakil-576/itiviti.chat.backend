package com.itiviti.chat.app.repository;

import com.itiviti.chat.app.constant.AppConstants;
import com.itiviti.chat.app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class CustomUserRepository {

    private final UserRepository userRepository;
    private final ReactiveMongoTemplate reactiveTemplate;

    public Mono<Boolean> isUserOnlineById(final String id) {
        final Aggregation fluxAggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("fullDocument._id").is(id)
                )
        );
        final ChangeStreamOptions options = ChangeStreamOptions.builder()
                .returnFullDocumentOnUpdate()
                .filter(fluxAggregation)
                .build();

        final Mono<User> userMono = userRepository.findById(id);

        final Flux<User> userMonoChangeStream = reactiveTemplate.changeStream("USERS", options, User.class)
                .map(ChangeStreamEvent::getBody);

        return Flux.merge(userMono, userMonoChangeStream)
                .any(user ->
                        ChronoUnit.SECONDS.between(
                                user.getLastLoginTime(), LocalDateTime.now()
                        ) < AppConstants.LAST_LOGIN_DIFFERENCE_IN_SECONDS
                ).or(Mono.just(Boolean.FALSE));
    }

    public Mono<User> registerOnlineActivity(final String id) {
        final User user = User.builder()
                .userName(id)
                .lastLoginTime(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }
}
