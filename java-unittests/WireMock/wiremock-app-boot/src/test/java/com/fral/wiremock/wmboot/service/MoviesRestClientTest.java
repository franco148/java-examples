package com.fral.wiremock.wmboot.service;

import com.fral.wiremock.wmboot.dto.Movie;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * https://rieckpil.de/spring-boot-integration-tests-with-wiremock-and-junit-5/
 * https://www.baeldung.com/junit-5-runwith
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 9191) //With this configuration the tests will not support response templating.
@TestPropertySource(properties = {
    "moviesapp.baseUrl=http://localhost:9191"
})
class MoviesRestClientTest {

    @Autowired
    MoviesRestClient moviesRestClient;

    @Test
    void retrieveAllMovies() {
        // Given
        stubFor(get(anyUrl())
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("all-movies.json"))
        );

        // When
        List<Movie> moviesList = moviesRestClient.retrieveAllMovies();
        System.out.println("MoviesList: " + moviesList);

        // Then
        assertTrue(moviesList.size() > 0);
    }

    @Test
    void retrieveMovieById_responseTemplating() {
        // Given
        stubFor(get(urlPathMatching("/movieservice/v1/movie/[0-9]"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-template.json")));
        Integer movieId = 9;

        // When
        Movie movie = moviesRestClient.retrieveMovieById(movieId);

        // Then
        assertEquals("Batman Begins", movie.getName());
        assertEquals(movieId, movie.getMovie_id().intValue());
    }
}