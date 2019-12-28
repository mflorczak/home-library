package com.pk.home.library.library.factory;

import com.pk.home.library.library.model.NumberOfBook;
import com.pk.home.library.library.model.Statistic;
import com.pk.home.library.library.service.AuthorService;
import com.pk.home.library.library.service.BookService;

public class NumberOfBookFactory implements StatisticAbstractFactory {

    private BookService bookService;
    private AuthorService authorService;

    public NumberOfBookFactory(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Statistic createStatistic() {
        return new NumberOfBook(bookService, authorService);
    }
}
