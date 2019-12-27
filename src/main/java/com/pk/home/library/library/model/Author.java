package com.pk.home.library.library.model;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {

    public interface JsonViews {
        interface Id {
        }

        interface Name extends Id {
        }

        interface NameWithBooks extends Name, Book.JsonViews.Title {
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Id.class)
    private Long id;

    @JsonView(JsonViews.Name.class)
    private String name;
    @JsonView(JsonViews.Name.class)
    private String surname;

    @OneToMany(mappedBy = "author")
    @JsonView(JsonViews.NameWithBooks.class)
    private List<Book> books = new ArrayList<>();

}
