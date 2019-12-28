package com.pk.home.library.library.model;

import com.pk.home.library.library.service.AuthorService;
import com.pk.home.library.library.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfBookPerAuthor extends Statistic {

    private BookService bookService;
    private AuthorService authorService;

    public NumberOfBookPerAuthor(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public DifferentStatistic calculateStatistic() {
        return new DifferentStatistic
                .DifferentStatisticBuilder()
                .setBooksPerAuthor(convertToMap(authorService.getAuthorRepository().countBooksPerAuthor()))
                .build();
    }

    private Map<Author, Long> convertToMap(List<Object[]> authorWithBooksQuantity) {
        Map<Author, Long> authorWithBooks = new HashMap<>();

        for (Object[] object: authorWithBooksQuantity) {
            Author author = new Author();
            author.setName(object[0].toString());
            author.setSurname(object[1].toString());
            authorWithBooks.put(author, (long) object[2]);
        }
        return authorWithBooks;
    }
}
