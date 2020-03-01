package com.fral.wiremock.service;

import com.fral.wiremock.dto.Movie;
import com.fral.wiremock.exception.MovieErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
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

    @Test
    void retrieveMovieByYear() {
        // Given
        Integer year = 2012;

        // When
        List<Movie> movieList = moviesRestClient.retrieveMovieByYear(year);

        // Then
        assertEquals(2, movieList.size());
    }

    @Test
    void retrieveMovieByYear_NotFound() {
        // Given
        Integer year = 1950;

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, () -> moviesRestClient.retrieveMovieByYear(year));
    }

    @Test
    void addNewMovie() {
        // Given
        Movie movie = new Movie(null, "Toys Story 4", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));

        // When
        Movie addedMovie = moviesRestClient.addNewMovie(movie);

        // Then
        assertNotNull(addedMovie.getMovie_id());
    }

    @Test
    void addNewMovie_BadRequest() {
        // Given
        Movie movie = new Movie(null, null, 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));

        // Then
        String expectedErrorMessage = "Please pass all the input fields : [name]";
        Assertions.assertThrows(MovieErrorResponse.class, () -> moviesRestClient.addNewMovie(movie), expectedErrorMessage);
    }

    @Test
    void updateMovie() {
        // Given
        Integer movieId = 3;
        String cast = "ABC";
        Movie movie = new Movie(null, null, null, cast, null);

        // When
        Movie updatedMovie = moviesRestClient.updateMovie(movieId, movie);

        // Then
        assertTrue(updatedMovie.getCast().contains(cast));
    }

    @Test
    void updateMovie_NotFound() {
        // Given
        Integer movieId = 103;
        String cast = "ABC";
        Movie movie = new Movie(null, null, null, cast, null);

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, ()->moviesRestClient.updateMovie(movieId, movie));
    }
}