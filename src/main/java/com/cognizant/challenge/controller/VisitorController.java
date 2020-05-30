package com.cognizant.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("visitor")
public class VisitorController {

    private final ReactiveRedisOperations<String, String> redisOps;
    private static final String PAGE_VIEW = "pageView";

    @GetMapping
    public String pageViews(){
        Long pageView = Long.valueOf(redisOps.keys(PAGE_VIEW).blockFirst()) + 1;
        redisOps.opsForValue().set(PAGE_VIEW, String.valueOf(pageView));
        return String.format("‘This is the %s visitor’",pageView);
    }
}
