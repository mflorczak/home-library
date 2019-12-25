package com.pk.home.library.library.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    public interface JsonViews {
        interface Title {
        }

        interface Name extends Title, Author.JsonViews.Name {
        }

        interface Get extends Name {
        }
    }

    @Id
    @GeneratedValue
    @JsonView(JsonViews.Name.class)
    private Long id;

    @NotNull
    @JsonView(JsonViews.Title.class)
    private String title;

    @NotNull
    @JsonView(JsonViews.Get.class)
    private String description;

    @ManyToOne
    @JsonView(JsonViews.Name.class)
    private Author author;

    public Book(@NotNull String title, @NotNull String description, @NotNull Author author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
