package com.pk.home.library.library.factory;

import com.pk.home.library.library.model.Statistic;

public class StatisticFactory {

    public static Statistic getStatistic(StatisticAbstractFactory statisticAbstractFactory) {
        return statisticAbstractFactory.createStatistic();
    }
}
