package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
