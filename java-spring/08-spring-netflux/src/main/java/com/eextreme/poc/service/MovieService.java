package com.eextreme.poc.service;

import com.eextreme.poc.domain.Movie;
import com.eextreme.poc.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    /**
     * Generate stream of movie events for given movie id
     * @param movieId
     * @return
     */
    Flux<MovieEvent> events(String movieId);

    Mono<Movie> findById(String id);

    Flux<Movie> findAll();
}
