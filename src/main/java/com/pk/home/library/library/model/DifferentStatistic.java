package com.pk.home.library.library.model;


import java.util.Map;

public class DifferentStatistic {

    private Long allBooksNumber;
    private Map<Author, Long> booksPerAuthor;

    public long getAllBooksNumber() {
        return allBooksNumber;
    }

    public Map<Author, Long> getBooksPerAuthor() {
        return booksPerAuthor;
    }

    private DifferentStatistic(DifferentStatisticBuilder builder) {
        allBooksNumber = builder.allBooksNumber;
        booksPerAuthor = builder.booksPerAuthor;
    }

    public static class DifferentStatisticBuilder {

        private long allBooksNumber;
        private Map<Author, Long> booksPerAuthor;

        public DifferentStatisticBuilder setAllBooksNumber(Long allBooksNumber) {
            this.allBooksNumber = allBooksNumber;
            return this;
        }

        public DifferentStatisticBuilder setBooksPerAuthor(Map<Author, Long> booksPerAuthor) {
            this.booksPerAuthor = booksPerAuthor;
            return this;
        }

        public DifferentStatistic build() {
            return new DifferentStatistic(this);
        }
    }
}
