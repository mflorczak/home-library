package com.pk.home.library.library.factory;

import com.pk.home.library.library.model.NumberOfBook;
import com.pk.home.library.library.model.Statistic;
import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;

public class NumberOfBookFactory implements StatisticAbstractFactory {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Override
    public Statistic createStatistic() {
        return new NumberOfBook(bookRepository, authorRepository);
    }

    public NumberOfBookFactory(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
}
