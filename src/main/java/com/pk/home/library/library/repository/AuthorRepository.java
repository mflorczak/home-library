package com.pk.home.library.library.repository;

import com.pk.home.library.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT a.name, a.surname, COUNT(b.id) FROM Author a\n" +
            "INNER JOIN a.books b\n" +
            "GROUP BY a.name, a.surname")
    List<Object[]> countBooksPerAuthor();
}
