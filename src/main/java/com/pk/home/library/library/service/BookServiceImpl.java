package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(final Book book) {
       return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(final Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(final Book book) {
        bookRepository.delete(book);
    }
}
