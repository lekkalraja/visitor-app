package com.cognizant.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service For visitor application
 */
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private final ReactiveRedisOperations<String, String> redisOps;
    private static final String PAGE_VIEW = "pageView";

    /**
     * Provides visitor counter
     * @return
     */
    @Override
    public Mono<String> getVisitorCount(){
        return redisOps.opsForValue().get(PAGE_VIEW).map(pageView -> {
            long views = Long.valueOf(pageView) + 1l;
            setPageViewCount(views);
            return String.format("‘This is the %s visitor’", pageView);
        });
    }

    /**
     * Initialises Page view count in the redis
     */
    @Override
    public void initialiseCount(){
        // For Demo, initializing page_view key into the redis
        setPageViewCount(0l);
    }

    /**
     * Set the Page view count in redis for pageView Key
     * @param count
     */
    private void setPageViewCount(long count){
        redisOps.opsForValue().set(PAGE_VIEW, String.valueOf(count))
                .thenMany(redisOps.keys(PAGE_VIEW)
                .flatMap(redisOps.opsForValue()::get))
                .subscribe(System.out::println);
    }
}