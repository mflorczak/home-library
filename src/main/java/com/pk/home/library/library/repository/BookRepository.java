package com.pk.home.library.library.repository;

import com.pk.home.library.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
