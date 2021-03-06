package com.pk.home.library.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.service.BookService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @JsonView(Book.JsonViews.TitleWithAuthorData.class)
    ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    @JsonView(Book.JsonViews.TitleWithAuthorExtended.class)
    ResponseEntity<Optional<Book>> book(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping()
    @JsonView(Book.JsonViews.TitleWithAuthorId.class)
    ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(newBook));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/search")
    @JsonView(Book.JsonViews.TitleWithAuthorData.class)
    public ResponseEntity<List<Book>> findByFilter(@RequestParam(required = false) String title, @RequestParam(required = false) String desc, @RequestParam(required = false) String authorName, @RequestParam(required = false) String publisher) {
        return bookService.findByFilter(title, desc, authorName, publisher)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/download/{fileFormat}")
    public ResponseEntity<InputStreamResource> downloadBooks(@PathVariable String fileFormat) throws JAXBException, IOException {
        File file = bookService.downloadBooks(fileFormat);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attatchment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_XML).contentLength(file.length())
                .body(resource);
    }
}



