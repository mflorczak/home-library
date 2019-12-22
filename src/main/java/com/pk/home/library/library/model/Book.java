package com.pk.home.library.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
