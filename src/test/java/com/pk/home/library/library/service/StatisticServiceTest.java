package com.pk.home.library.library.service;

import com.pk.home.library.library.enumtype.StatisticModes;
import com.pk.home.library.library.model.NumberOfBook;
import com.pk.home.library.library.model.NumberOfBookPerAuthor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticServiceTest {

    private StatisticService statisticService = new StatisticService(null, null);

    @Test
    public void checkInstanceOfNumberOfBook() {
        assertThat(statisticService.getStatistic(StatisticModes.ALL_BOOKS) instanceof NumberOfBook);
    }

    @Test
    public void checkInstanceOfNumberOfBooksPerAuthor() {
        assertThat(statisticService.getStatistic(StatisticModes.BOOKS_PER_AUTHOR) instanceof NumberOfBookPerAuthor);
    }

}