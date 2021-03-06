package com.fral.wiremock.service;

import com.fral.wiremock.constants.MoviesAppConstants;
import com.fral.wiremock.dto.Movie;
import com.fral.wiremock.exception.MovieErrorResponse;
import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(WireMockExtension.class)
class MoviesRestClientTest {

    MoviesRestClient moviesRestClient;
    WebClient webClient;

    @InjectServer
    WireMockServer wireMockServer;

    @ConfigureWireMock
    Options options = wireMockConfig().port(9191)
                                      .notifier(new ConsoleNotifier(true))
                                      .extensions(new ResponseTemplateTransformer(true));

    @BeforeEach
    void setUp() {
        int port = wireMockServer.port();
        String baseUrl = String.format("http://localhost:%s", port);
//        String baseUrl = "http://localhost:8081";
        webClient = WebClient.create(baseUrl);
        moviesRestClient = new MoviesRestClient(webClient);

        // Proxy
        stubFor(any(anyUrl()).willReturn(aResponse().proxiedFrom("http://localhost:8081")));
    }

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
    void retrieveAllMovies_MatchesUrl() {
        // Given
        stubFor(get(urlPathEqualTo(MoviesAppConstants.GET_ALL_MOVIES_V1))
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
    void retrieveMovieById() {
        // Given
//        stubFor(get(urlPathEqualTo("/movieservice/v1/movie/1"))
        stubFor(get(urlPathMatching("/movieservice/v1/movie/[0-9]"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("movie.json")));
        Integer movieId = 1;

        // When
        Movie movie = moviesRestClient.retrieveMovieById(movieId);

        // Then
        assertEquals("Batman Begins", movie.getName());
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

    @Test
    void retrieveMovieById_NotFound() {
        // Given
        stubFor(get(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("404-movieId.json")));
        Integer movieId = 100;

        // Then
//        Assertions.assertThrows(WebClientResponseException.class, () -> moviesRestClient.retrieveMovieById(movieId));
        Assertions.assertThrows(MovieErrorResponse.class, () -> moviesRestClient.retrieveMovieById(movieId));
    }

    @Test
    void retrieveMovieByName() {
        // Given
        String movieName = "Avengers";
        stubFor(get(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name="+movieName))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("avengers.json")));

        // When
        List<Movie> movieList = moviesRestClient.retrieveMovieByName(movieName);

        // Then
        assertEquals(4, movieList.size());
    }

    @Test
    void retrieveMovieByName_responseTemplating() {

        //given
        String movieName = "Avengers";
        stubFor(get(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name="+movieName))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-byName-template.json")));


        //when
        List<Movie> movieList = moviesRestClient.retrieveMovieByName(movieName);

        //then
        String castExpected = "Robert Downey Jr, Chris Evans , Chris HemsWorth";
        assertEquals(4, movieList.size());
        assertEquals(castExpected, movieList.get(0).getCast());

    }

    @Test
    void retrieveMovieByName_approach2() {

        //given
        String movieName = "Avengers";
        stubFor(get(urlPathEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1))
                // .withQueryParam("movie_name", equalTo(movieName) )
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("avengers.json")));


        //when
        List<Movie> movieList = moviesRestClient.retrieveMovieByName(movieName);

        //then
        String castExpected = "Robert Downey Jr, Chris Evans , Chris HemsWorth";
        assertEquals(4, movieList.size());
        assertEquals(castExpected, movieList.get(0).getCast());

    }

    @Test
    void retrieveMovieByName_NotFound() {
        // Given
        String movieName = "ABC";
        stubFor(get(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name="+movieName))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("404-movieName.json")));

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, ()-> moviesRestClient.retrieveMovieByName(movieName));
    }

    @Test
    void retrieveMovieByYear() {
        // Given
        Integer year = 2012;
        stubFor(get(urlEqualTo(MoviesAppConstants.MOVIE_BY_YEAR_QUERY_PARAM_V1+"?year="+year))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("year-template.json")));

        // When
        List<Movie> movieList = moviesRestClient.retrieveMovieByYear(year);

        // Then
        assertEquals(2, movieList.size());
    }

    @Test
    void retrieveMovieByYear_NotFound() {
        // Given
        Integer year = 1950;
        stubFor(get(urlEqualTo(MoviesAppConstants.MOVIE_BY_YEAR_QUERY_PARAM_V1+"?year="+year))
                .withQueryParam("year", equalTo(year.toString()))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("404-movieyear.json")));

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, () -> moviesRestClient.retrieveMovieByYear(year));
    }

    @Test
    void addNewMovie() {
        // Given
        Movie movie = new Movie(null, "Toys Story 4", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));
        stubFor(post(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                .withRequestBody(matchingJsonPath(("$.name"),equalTo("Toys Story 4")))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom")))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("add-movie.json")));

        // When
        Movie addedMovie = moviesRestClient.addNewMovie(movie);

        // Then
        assertNotNull(addedMovie.getMovie_id());
    }

    @Test
    void addMovie_responseTemplating() {
        //given
        Movie movie = new Movie(null, "Toys Story 4", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));
        stubFor(post(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                // .withQueryParam("movie_name", equalTo(movieName) )
                .withRequestBody(matchingJsonPath(("$.name"),equalTo("Toys Story 4")))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom")))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("add-movie-template.json")));

        //when
        Movie addedMovie = moviesRestClient.addNewMovie(movie);
        System.out.println(addedMovie);

        //then
        assertTrue(addedMovie.getMovie_id() != null);
    }

    @Test
    void addNewMovie_BadRequest() {
        // Given
        Movie movie = new Movie(null, null, 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));
        stubFor(post(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom")))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("400-invalid-input.json")));

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
        stubFor(put(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
                .withRequestBody(matchingJsonPath(("$.cast"), containing(cast)))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("updatemovie-template.json")));

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
        stubFor(put(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
                .withRequestBody(matchingJsonPath(("$.cast"), containing(cast)))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, ()->moviesRestClient.updateMovie(movieId, movie));
    }

    @Test
    void deleteMovieById() {
        // Given
        Movie movie = new Movie(null, "Toys Story 5", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));
        stubFor(post(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                .withRequestBody(matchingJsonPath(("$.name"),equalTo("Toys Story 5")))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom")))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("add-movie-template.json")));

        Movie addedMovie = moviesRestClient.addNewMovie(movie);
        String expectedErrorMessage = "Movie Deleted Successfully";
        stubFor(delete(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(expectedErrorMessage)));

        // When
        String responseMessage = moviesRestClient.deleteMovieById(addedMovie.getMovie_id());

        // Then
        assertEquals(expectedErrorMessage, responseMessage);
    }

    @Test
    void deleteMovieById_NotFound() {
        // Given
        Long id = 100L;
        stubFor(delete(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        // Then
        Assertions.assertThrows(MovieErrorResponse.class, ()->moviesRestClient.deleteMovieById(id));
    }

    @Test
    void deleteMovieByName() {
        //given
        Movie movie = new Movie(null, "Toys Story 5", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));

        stubFor(post(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                .withRequestBody(matchingJsonPath(("$.name"),equalTo("Toys Story 5")))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom")))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("add-movie-template.json")));
        Movie addedMovie = moviesRestClient.addNewMovie(movie);

        String expectedErrorMessage = "Movie Deleted Successfully";
        stubFor(delete(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name=Toys%20Story%205"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        //when
        String responseMessage = moviesRestClient.deleteMovieByName(addedMovie.getName());

        //then
        assertEquals(expectedErrorMessage, responseMessage);

        verify(exactly(1),postRequestedFor(urlPathEqualTo(MoviesAppConstants.ADD_MOVIE_V1))
                .withRequestBody(matchingJsonPath(("$.name"),equalTo("Toys Story 5")))
                .withRequestBody(matchingJsonPath(("$.cast"), containing("Tom"))));

        verify(exactly(1),deleteRequestedFor(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name=Toys%20Story%205")));

    }

    /**
     * What is selective proxying?
     * - WireMock reverse proxies the actual service for some endpoints.
     *
     * - New functionalities to the existing service that is not yet implemented.
     */
//    @Test
    void deleteMovieByName_selectiveproxying() {
        //given
        Movie movie = new Movie(null, "Toys Story 5", 2019, "Tom Hanks, Tim Allen", LocalDate.of(2019, 06, 20));
        Movie addedMovie = moviesRestClient.addNewMovie(movie);

        String expectedErrorMessage = "Movie Deleted Successfully";
        stubFor(delete(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name=Toys%20Story%205"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        //when
        String responseMessage = moviesRestClient.deleteMovieByName(addedMovie.getName());

        //then
        assertEquals(expectedErrorMessage, responseMessage);

        verify(exactly(1),deleteRequestedFor(urlEqualTo(MoviesAppConstants.MOVIE_BY_NAME_QUERY_PARAM_V1+"?movie_name=Toys%20Story%205")));

    }
}