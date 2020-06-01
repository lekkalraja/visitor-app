package com.cognizant.challenge.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VisitorServiceTest {

    @InjectMocks private VisitorService visitorService;
    @Mock private ReactiveRedisOperations<String,String> reactiveRedisOperations;
    @Mock private ReactiveValueOperations reactiveValueOperations;

    private static final String NEXT_VIEW = "2";
    private static final String CURRENT_VIEW = "1";

    @Before
    public void setup(){
        when(reactiveRedisOperations.opsForValue()).thenReturn(reactiveValueOperations);
        when(reactiveValueOperations.get(anyString())).thenReturn(Mono.just(CURRENT_VIEW));
        when(reactiveValueOperations.set(anyString(), anyString())).thenReturn(Mono.just(NEXT_VIEW));
        when(reactiveRedisOperations.keys(anyString())).thenReturn(Flux.just(NEXT_VIEW));
    }

    @Test
    public void shouldGetVisitorCount(){
        //Given

        //When
        Mono<String> visitorCount = visitorService.getVisitorCount();

        //Then
        StepVerifier.create(visitorCount)
                .expectNext(String.format("‘This is the %s visitor’", CURRENT_VIEW))
                .verifyComplete();
    }

    @Test
    public void shouldInitialiseCount(){
        //Given

        //When
        visitorService.initialiseCount();

        //Then
        StepVerifier.create(visitorService.getVisitorCount())
                .expectNext(String.format("‘This is the %s visitor’", CURRENT_VIEW))
                .verifyComplete();
    }

}