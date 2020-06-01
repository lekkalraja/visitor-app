package com.cognizant.challenge.controller;

import com.cognizant.challenge.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * Controller for Visitor Application
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("visitor")
public class VisitorController {

    private final VisitorService visitorService;

    /**
     * Endpoint to retrive the page views
     * @return Visior Count
     */
    @GetMapping
    public Mono<String> pageViews(){
        return visitorService.getVisitorCount();
    }

    /**
     * Initialises the redis key with default value (0) for demo.
     */
    @PostConstruct
    public void init(){
        visitorService.initialiseCount();
    }
}