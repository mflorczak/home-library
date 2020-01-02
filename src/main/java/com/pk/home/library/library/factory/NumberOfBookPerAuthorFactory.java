package com.pk.home.library.library.factory;

import com.pk.home.library.library.model.NumberOfBookPerAuthor;
import com.pk.home.library.library.model.Statistic;
import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberOfBookPerAuthorFactory implements StatisticAbstractFactory {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Override
    public Statistic createStatistic() {
        return new NumberOfBookPerAuthor(bookRepository, authorRepository);
    }
}
