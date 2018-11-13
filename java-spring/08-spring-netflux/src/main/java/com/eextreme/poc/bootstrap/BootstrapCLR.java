package com.eextreme.poc.bootstrap;

import com.eextreme.poc.domain.Movie;
import com.eextreme.poc.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //clear old data
        movieRepository.deleteAll().block();

        Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                  "Back to the future", "Meet the Fluxes", "Lord of the Fluxes")
                .map(title -> new Movie(UUID.randomUUID().toString(), title))
                .flatMap(movieRepository::save)
                .subscribe(null, null, ()-> {
                    movieRepository.findAll().subscribe(System.out::println);
                });
    }
}
