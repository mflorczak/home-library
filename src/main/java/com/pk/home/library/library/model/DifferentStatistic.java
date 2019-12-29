package com.pk.home.library.library.model;


import java.util.List;

public class DifferentStatistic {

    private Long allBooksNumber;
    private List<AuthorStatistic> booksPerAuthor;

    public long getAllBooksNumber() {
        return allBooksNumber;
    }

    public List<AuthorStatistic> getBooksPerAuthor() {
        return booksPerAuthor;
    }

    private DifferentStatistic(DifferentStatisticBuilder builder) {
        allBooksNumber = builder.allBooksNumber;
        booksPerAuthor = builder.booksPerAuthor;
    }

    public static class DifferentStatisticBuilder {

        private long allBooksNumber;
        private List<AuthorStatistic> booksPerAuthor;

        public DifferentStatisticBuilder setAllBooksNumber(Long allBooksNumber) {
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
