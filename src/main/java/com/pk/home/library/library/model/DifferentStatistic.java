package com.pk.home.library.library.model;


import lombok.Data;

import java.util.List;

@Data
public class DifferentStatistic {

    private long allBooksNumber;
    private List<AuthorStatistic> booksPerAuthor;

    private DifferentStatistic(DifferentStatisticBuilder builder) {
        allBooksNumber = builder.allBooksNumber;
        booksPerAuthor = builder.booksPerAuthor;
    }

    public static class DifferentStatisticBuilder {

        private long allBooksNumber;
        private List<AuthorStatistic> booksPerAuthor;

        public DifferentStatisticBuilder setAllBooksNumber(long allBooksNumber) {
            this.allBooksNumber = allBooksNumber;
            return this;
        }

        public DifferentStatisticBuilder setBooksPerAuthor(List<AuthorStatistic> booksPerAuthor) {
            this.booksPerAuthor = booksPerAuthor;
            return this;
        }

        public DifferentStatistic build() {
            return new DifferentStatistic(this);
        }
    }
}
