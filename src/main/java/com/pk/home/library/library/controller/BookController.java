package com.pk.home.library.library.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    @JsonView(Book.JsonViews.Name.class)
    List<Book> all() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Book.JsonViews.Get.class)
    Book book(@PathVariable Long id) {
        return bookRepository.getOne(id);
    }

    @PostMapping()
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }
}



