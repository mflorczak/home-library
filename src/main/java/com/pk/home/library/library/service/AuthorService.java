package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(@NotNull Author author) {
        return authorRepository.save(author);
    }

    public ResponseEntity<Void> deleteAuthor(@NotNull Long authorId) {
        authorRepository.deleteById(authorId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(@NotNull Long id) {
        return authorRepository.findById(id);
    }
}
