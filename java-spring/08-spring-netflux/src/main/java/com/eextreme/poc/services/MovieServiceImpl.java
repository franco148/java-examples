package com.eextreme.poc.services;

import com.eextreme.poc.domain.Movie;
import com.eextreme.poc.domain.MovieEvent;
import com.eextreme.poc.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(movieId, new Date()));
        }).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Movie> findById(String id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> findAll() {
        return this.movieRepository.findAll();
    }
}
