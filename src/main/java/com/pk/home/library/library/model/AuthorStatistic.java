package com.pk.home.library.library.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorStatistic {

    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private long numberOfBooks;

    public AuthorStatistic(String name, String surname, long numberOfBooks) {
        this.name = name;
        this.surname = surname;
        this.numberOfBooks = numberOfBooks;
    }
}
