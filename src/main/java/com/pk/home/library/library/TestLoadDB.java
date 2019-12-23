package com.pk.home.library.library;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TestLoadDB {
    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {
            Author author = new Author("imie", "nazwisko");
            Book book = new Book("tytul", "desc", author);
            authorRepository.save(author);
            bookRepository.save(book);
        };
    }
}
