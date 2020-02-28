package com.fral.wiremock.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long movie_id;

    private String name;

    private Integer year;

    private String cast;

    private LocalDate release_date;
}

