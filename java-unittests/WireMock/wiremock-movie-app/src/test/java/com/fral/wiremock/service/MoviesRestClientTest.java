package com.fral.wiremock.service;

import com.fral.wiremock.dto.Movie;
import com.fral.wiremock.exception.MovieErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesRestClientTest {

    MoviesRestClient moviesRestClient;
    WebClient webClient;

    @BeforeEach
    void setUp() {
        String baseUrl = "http://localhost:8081";
        webClient = WebClient.create(baseUrl);
        moviesRestClient = new MoviesRestClient(webClient);
    }

    @Test
    void retrieveAllMovies() {
        // When
        List<Movie> moviesList = moviesRestClient.retrieveAllMovies();
        System.out.println("MoviesList: " + moviesList);

        // Then
        assertTrue(moviesList.size() > 0);
    }

    @Test
    void retrieveMovieById() {
        // Given
        Integer movieId = 1;

        // When
        Movie movie = moviesRestClient.retrieveMovieById(movieId);

        // Then
        assertEquals("Batman Begins", movie.getName());
    }

    @Test
    void retrieveMovieById_NotFound() {
        // Given
        Integer movieId = 100;

        // Then
//        Assertions.assertThrows(WebClientResponseException.class, () -> moviesRestClient.retrieveMovieById(movieId));
        Assertions.assertThrows(MovieErrorResponse.class, () -> moviesRestClient.retrieveMovieById(movieId));
    }

    @Test
    void retrieveMovieByName() {
        // Given
        String movieName = "Avengers";

        // When
        List<Movie> movieList = moviesRestClient.retrieveMovieByName(movieName);

        // Then
        assertEquals(4, movieList.size());
    }

    @Test
    void retrieveMovieByName_NotFound() {
        // Given
        String movieName = "ABC";

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, ()-> moviesRestClient.retrieveMovieByName(movieName));
    }
}