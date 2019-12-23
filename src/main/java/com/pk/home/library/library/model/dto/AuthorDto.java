package com.pk.home.library.library.model.dto;

import com.pk.home.library.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {

    private String name;
    private String surname;
    private List<Book> books;
}
