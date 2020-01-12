package com.pk.home.library.library.parser;

import com.pk.home.library.library.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class BookToXMLParser implements Parser {
    //private static final String FILE_PATH = "E:/temp/books.xml";
    @Override
    public void serialize(List<Book> books, File file) throws JAXBException {

        BookListWrapper bookListWrapper = new BookListWrapper();
        bookListWrapper.setBooks(books);
        JAXBContext ctx = JAXBContext.newInstance(BookListWrapper.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bookListWrapper, file);
        //return file;
    }
}
