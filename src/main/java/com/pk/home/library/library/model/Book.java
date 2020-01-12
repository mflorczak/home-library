package com.pk.home.library.library.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Title.class)
    @XmlTransient
    private Long id;

    @NotNull
    @JsonView(JsonViews.Title.class)
    @XmlElement(name = "title")
    private String title;

    @NotNull
    @JsonView(JsonViews.TitleWithAuthorExtended.class)
    private String publisher;

    @NotNull
    @JsonView(JsonViews.TitleWithAuthorExtended.class)
    private String description;

    @ManyToOne()
    @JsonView(JsonViews.TitleWithAuthorId.class)
    @XmlElement(name = "author")
    private Author author;
}
