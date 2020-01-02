package com.pk.home.library.library.repository;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.model.AuthorStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT " +
            "new com.pk.home.library.library.model.AuthorStatistic(a.name, a.surname, COUNT(b.id)) " +
            "FROM Author a " +
            "INNER JOIN a.books b " +
            "GROUP BY a.name, a.surname")
    List<AuthorStatistic> countBooksPerAuthor();
}
