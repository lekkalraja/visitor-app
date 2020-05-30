package com.cognizant.challenge.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("visitor")
public class VisitorController {

    private final ReactiveRedisOperations<String, String> redisOps;
    private static final String PAGE_VIEW = "pageView";

    @GetMapping
    public Mono<String> pageViews(){
        return redisOps.opsForValue().get(PAGE_VIEW).map(pageView -> {
            long views = Long.valueOf(pageView) + 1l;
            redisOps.opsForValue().set(PAGE_VIEW, String.valueOf(views))
                    .thenMany(redisOps.keys("*")
                    .flatMap(redisOps.opsForValue()::get))
                    .subscribe(System.out::println);;
            return String.format("‘This is the %s visitor’", pageView);
        });
    }

    @PostConstruct
    public void init(){
        // For Demo, initializing page_view key into the redis
        redisOps.opsForValue().set(PAGE_VIEW, String.valueOf(0))
                .thenMany(redisOps.keys("*")
                .flatMap(redisOps.opsForValue()::get))
                .subscribe(System.out::println);
    }
}