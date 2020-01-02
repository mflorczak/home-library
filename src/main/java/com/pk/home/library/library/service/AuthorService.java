package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.repository.AuthorRepository;
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

    public boolean deleteAuthor(@NotNull Long authorId) {
        if (authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return true;
        } else {
            return false;
        }
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(@NotNull Long id) {
        return authorRepository.findById(id);
    }
}
