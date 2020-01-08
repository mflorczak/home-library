package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Author;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.parser.Parser;
import com.pk.home.library.library.parser.ParserFactory;
import com.pk.home.library.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    @PersistenceContext
    EntityManager entityManager;

    public Book addBook(@NotNull Book book) {
        return bookRepository.save(book);
    }

    public boolean deleteBook(@NotNull Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(@NotNull Long id) {
        return bookRepository.findById(id);
    }

    private boolean isFieldNameValid(String fieldName) {
        return fieldName != null && !fieldName.isEmpty();
    }

    private String likePattern(String fieldName) {
        return "%" + fieldName.toLowerCase() + "%";
    }

    public Optional<List<Book>> findByFilter(String title, String desc, String name, String publisher) {
        String[] nameElements;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);
        Join<Book, Author> bookAuthorJoin = bookRoot.join("author", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        if (isFieldNameValid(title)) {
            predicates.add(cb.like(cb.lower(bookRoot.get("title")), likePattern(title)));
        }
        if (isFieldNameValid(desc)) {
            predicates.add(cb.like(cb.lower(bookRoot.get("description")), likePattern(desc)));
        }

        if (isFieldNameValid(name)) {
            nameElements = name.split(" ");

            if (nameElements.length == 1) {
                Predicate namePredicate = cb.like(cb.lower(bookAuthorJoin.get("name")), likePattern(nameElements[0]));
                Predicate surnamePredicate = cb.like(cb.lower(bookAuthorJoin.get("surname")), likePattern(nameElements[0]));
                predicates.add(cb.or(namePredicate, surnamePredicate));
            } else {
                Predicate namePredicate = cb.like(cb.lower(bookAuthorJoin.get("name")), likePattern(nameElements[0]));
                Predicate surnamePredicate = cb.like(cb.lower(bookAuthorJoin.get("surname")), likePattern(nameElements[1]));
                predicates.add(cb.and(namePredicate, surnamePredicate));
            }
        }

        if (isFieldNameValid(publisher)) {
            predicates.add(cb.like(cb.lower(bookRoot.get("publisher")), likePattern(publisher)));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return Optional.of(entityManager.createQuery(cq).getResultList());
    }

    public File downloadBooks(String fileFormat) throws JAXBException, IOException {
        if ("csv".equalsIgnoreCase(fileFormat) || "xml".equalsIgnoreCase(fileFormat)) {
            File file = File.createTempFile("books", "." + fileFormat.toLowerCase());
            ParserFactory parserFactory = new ParserFactory();
            Parser parser = parserFactory.getParser(fileFormat);
            parser.serialize(bookRepository.findAll(), file);
            return file;
        } else {
            throw new IllegalArgumentException("file format not supported");
        }
    }
}