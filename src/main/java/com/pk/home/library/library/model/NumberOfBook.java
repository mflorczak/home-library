package com.pk.home.library.library.model;


import com.pk.home.library.library.service.AuthorService;
import com.pk.home.library.library.service.BookService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberOfBook extends Statistic {

    private BookService bookService;
    private AuthorService authorService;

    @Override
    public DifferentStatistic calculateStatistic() {
        return new DifferentStatistic
                .DifferentStatisticBuilder()
                .setAllBooksNumber(bookService.getBookRepository().count())
                .build();
    }
}
