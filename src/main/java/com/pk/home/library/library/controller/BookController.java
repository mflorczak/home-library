package com.pk.home.library.library.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @JsonView(Book.JsonViews.Name.class)
    ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    @JsonView(Book.JsonViews.Get.class)
    ResponseEntity<Optional<Book>> book(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping()
    @JsonView(Book.JsonViews.Title.class)
    ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(newBook));
    }

    @ResponseStatus(HttpStatus.OK)
    void deleteBook(@NotNull Book book) {
        bookService.deleteBook(book);
    }

    @GetMapping("/search")
    @JsonView(Book.JsonViews.Get.class)
    public ResponseEntity<List<Book>> findByFilter(@RequestParam(required = false) String title, @RequestParam(required = false) String desc) {
        return bookService.findByFilter(title, desc)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}



