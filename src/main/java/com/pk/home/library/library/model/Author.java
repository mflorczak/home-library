package com.pk.home.library.library.model;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {

    public interface JsonViews {
        interface Name {
        }

        interface Get extends Name, Book.JsonViews.Title {
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonView(JsonViews.Name.class)
    private String name;
    @JsonView(JsonViews.Name.class)
    private String surname;

    @OneToMany(mappedBy = "author")
    @JsonView(JsonViews.Get.class)
    private List<Book> books;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.books = new ArrayList<>();
    }

}
