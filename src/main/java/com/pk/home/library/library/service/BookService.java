package com.pk.home.library.library.service;

import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.parser.Parser;
import com.pk.home.library.library.parser.ParserFactory;
import com.pk.home.library.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private static final String FILE_PATH = "E:/temp/books.xml";

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(@NotNull Book book) {
        return bookRepository.save(book);
    }

    public ResponseEntity<Void> deleteBook(@NotNull Long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(@NotNull Long id) {
        return bookRepository.findById(id);
    }


    public Optional<List<Book>> findByFilter(String title, String desc) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        if (title != null && !"".equals(title)) {
            predicates.add(cb.like(bookRoot.get("title"), "%" + title + "%"));
        }
        if (desc != null && !"".equals(desc)) {
            predicates.add(cb.like(bookRoot.get("description"), "%" + desc + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return Optional.of(entityManager.createQuery(cq).getResultList());
    }

    public ResponseEntity<InputStreamResource> downloadBooks(String fileFormat) throws JAXBException, FileNotFoundException {
        File file = new File(FILE_PATH);

        ParserFactory parserFactory = new ParserFactory();
        Parser parser = parserFactory.getParser(fileFormat);
        parser.serialize(bookRepository.findAll(), file);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attatchment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_XML).contentLength(file.length())
                .body(resource);
    }
}