package com.pk.home.library.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping()
    @JsonView(Author.JsonViews.Name.class)
    List<Author> all() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Author.JsonViews.Get.class)
    Author author(@PathVariable Long id) {
        return authorRepository.getOne(id);
    }
}
