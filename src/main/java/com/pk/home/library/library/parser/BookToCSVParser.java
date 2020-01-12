package com.pk.home.library.library.parser;

import com.pk.home.library.library.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BookToCSVParser implements Parser {
    @Override
    public void serialize(List<Book> books, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT
                .withHeader("id", "title", "publisher", "description", "author_id", "author_name", "author_surname"));
        for (Book book : books) {
            csvPrinter.printRecord((String.valueOf(book.getId())), book.getTitle(), book.getPublisher(), book.getDescription(), String.valueOf(book.getAuthor().getId()), book.getAuthor().getName(), book.getAuthor().getSurname());
            csvPrinter.flush();
        }
    }
}
