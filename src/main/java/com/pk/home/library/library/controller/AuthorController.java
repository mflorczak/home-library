package com.pk.home.library.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    @GetMapping()
    @JsonView(Author.JsonViews.Name.class)
    ResponseEntity<List<Author>> all() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }

    @GetMapping("/{id}")
    @JsonView(Author.JsonViews.NameWithBooks.class)
    ResponseEntity<Optional<Author>> author(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @PostMapping()
    @JsonView(Author.JsonViews.Name.class)
    ResponseEntity<Author> addBook(@RequestBody Author newAuthor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(newAuthor));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteAuthor(@PathVariable Long id) {
        if (authorService.deleteAuthor(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
