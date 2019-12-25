package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(@NotNull Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(@NotNull Book book) {
        bookRepository.delete(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(@NotNull Long id) {
        return bookRepository.findById(id);
    }

}
