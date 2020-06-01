package com.cognizant.challenge.service;

import reactor.core.publisher.Mono;

/**
 * Visitor Service
 */
public interface VisitorService {

    /**
     * Gets visitor Count
     * @return
     */
    Mono<String> getVisitorCount();

    /**
     * Initialise with default(inital) count
     */
    void initialiseCount();
}
