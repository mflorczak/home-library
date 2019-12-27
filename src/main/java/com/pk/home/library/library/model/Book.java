package com.pk.home.library.library.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    public interface JsonViews {
        interface Title {
        }

        interface TitleWithAuthorId extends Title, Author.JsonViews.Id {
        }

        interface TitleWithAuthorData extends TitleWithAuthorId, Author.JsonViews.Name {
        }

        interface TitleWithAuthorExtended extends TitleWithAuthorData {
        }

    }

    @Id
    @GeneratedValue
    @JsonView(JsonViews.Title.class)
    private Long id;

    @NotNull
    @JsonView(JsonViews.Title.class)
    private String title;

    @NotNull
    @JsonView(JsonViews.TitleWithAuthorExtended.class)
    private String publisher;

    @NotNull
    @JsonView(JsonViews.TitleWithAuthorExtended.class)
    private String description;

    @ManyToOne
    @JsonView(JsonViews.TitleWithAuthorId.class)
    private Author author;

    public Book(@NotNull String title, @NotNull String description, @NotNull Author author, @NotNull String publisher) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
    }
}
