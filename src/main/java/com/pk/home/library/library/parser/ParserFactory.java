package com.pk.home.library.library.parser;

public class ParserFactory {
    public Parser getParser(String fileFormat) {

        switch (fileFormat.toLowerCase()) {
            case "xml":
                return new BookToXMLParser();
            case "csv":
                return new BookToCSVParser();
            default:
                throw new IllegalArgumentException("file format not supported");

        }
    }
}
