package com.pk.home.library.library.model;

import com.pk.home.library.library.service.AuthorService;
import com.pk.home.library.library.service.BookService;

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
                .setBooksPerAuthor(authorService.getAuthorRepository().countBooksPerAuthor())
                .build();
    }
}
