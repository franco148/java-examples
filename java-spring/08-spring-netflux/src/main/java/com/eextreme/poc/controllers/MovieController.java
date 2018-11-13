package com.eextreme.poc.controllers;

import com.eextreme.poc.domain.Movie;
import com.eextreme.poc.domain.MovieEvent;
import com.eextreme.poc.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/{id}/events")
    Flux<MovieEvent> streamMovieEvents(@PathVariable("id") String id) {
        return this.movieService.events(id);
    }

    @GetMapping("/{id}")
    Mono<Movie> getMovieById(@PathVariable("id") String id) {
        return movieService.findById(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies() {
        return movieService.findAll();
    }
}
