package com.pk.home.library.library.model;


import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberOfBook extends Statistic {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Override
    public DifferentStatistic calculateStatistic() {
        return new DifferentStatistic
                .DifferentStatisticBuilder()
                .setAllBooksNumber(bookRepository.count())
                .build();
    }
}
