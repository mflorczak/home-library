package com.pk.home.library.library.controller;


import com.pk.home.library.library.mapper.BookMapper;
import com.pk.home.library.library.model.Book;
import com.pk.home.library.library.model.dto.BookDto;
import com.pk.home.library.library.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/book")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.accepted().body(bookMapper.mapToBookDto(bookService.saveBook(bookMapper.mapToBook(bookDto))));
    }

//    @PostMapping("/book")
//    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        return ResponseEntity.accepted().body(bookService.saveBook(book));
//    }
}
