package com.pk.home.library.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    @JsonView(Author.JsonViews.Name.class)
    ResponseEntity<List<Author>> all() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }

    @GetMapping("/{id}")
    @JsonView(Author.JsonViews.Get.class)
    ResponseEntity<Optional<Author>> author(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @PostMapping()
    @JsonView(Author.JsonViews.Name.class)
    ResponseEntity<Author> addBook(@RequestBody Author newAuthor) {
        return ResponseEntity.ok(authorService.addAuthor(newAuthor));
    }

    @ResponseStatus(HttpStatus.OK)
    void deleteAuthor(@NotNull Author author) {
        authorService.deleteAuthor(author);
    }

}
