package com.eextreme.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
@AllArgsConstructor
public class Movie {

    private String id;

    @NotNull
    private String title;
}
