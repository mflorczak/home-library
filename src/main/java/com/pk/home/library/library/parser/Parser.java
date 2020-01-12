package com.pk.home.library.library.parser;

import com.pk.home.library.library.model.Book;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
    void serialize(List<Book> books, File file) throws JAXBException, IOException;
}
