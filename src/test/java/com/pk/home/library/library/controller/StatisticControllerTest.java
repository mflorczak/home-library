package com.pk.home.library.library.controller;

import com.pk.home.library.library.enumtype.StatisticModes;
import com.pk.home.library.library.factory.NumberOfBookFactory;
import com.pk.home.library.library.factory.StatisticFactory;
import com.pk.home.library.library.model.AuthorStatistic;
import com.pk.home.library.library.model.DifferentStatistic;
import com.pk.home.library.library.model.NumberOfBook;
import com.pk.home.library.library.repository.AuthorRepository;
import com.pk.home.library.library.repository.BookRepository;
import com.pk.home.library.library.service.AuthorService;
import com.pk.home.library.library.service.BookService;
import com.pk.home.library.library.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private StatisticService statisticService;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void shouldReturnNumberOfBookInLibrary() throws Exception {
        when(bookService.getBookRepository()).thenReturn(bookRepository);
        when(bookService.getBookRepository().count()).thenReturn(6L);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/all-book")
                .param("mode", "ALL_BOOKS")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allBooksNumber", is(6)))
                .andExpect(jsonPath("$.booksPerAuthor").isEmpty());

    }

    @Test
    public void shouldReturnNumberOfBooksPerAuthor() throws Exception {
        List<AuthorStatistic> authorStatistics = Arrays.asList(new AuthorStatistic("Andrzej", "Sapkowski", 3),
                new AuthorStatistic("Joanne", "Rowling", 2));

        when(authorService.getAuthorRepository()).thenReturn(authorRepository);
        when(authorRepository.countBooksPerAuthor()).thenReturn(authorStatistics);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/all-book")
                .param("mode", "BOOKS_PER_AUTHOR")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allBooksNumber", is(0)))
                .andExpect(jsonPath("$.booksPerAuthor", hasSize(2)))
                .andExpect(jsonPath("$.booksPerAuthor.[0].name", is("Andrzej")))
                .andExpect(jsonPath("$.booksPerAuthor.[0].surname", is("Sapkowski")))
                .andExpect(jsonPath("$.booksPerAuthor.[0].numberOfBooks", is(3)));
    }
}