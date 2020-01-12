package com.pk.home.library.library.service;

import com.pk.home.library.library.enumtype.StatisticModes;
import com.pk.home.library.library.factory.NumberOfBookFactory;
import com.pk.home.library.library.factory.NumberOfBookPerAuthorFactory;
import com.pk.home.library.library.factory.StatisticFactory;
import com.pk.home.library.library.model.Statistic;
import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticService {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Statistic getStatistic(StatisticModes statisticModes) {
        if (statisticModes.equals(StatisticModes.ALL_BOOKS))
            return StatisticFactory.getStatistic(new NumberOfBookFactory(bookRepository, authorRepository));

        if (statisticModes.equals(StatisticModes.BOOKS_PER_AUTHOR))
            return StatisticFactory.getStatistic(new NumberOfBookPerAuthorFactory(bookRepository, authorRepository));
        return null;
    }
}
