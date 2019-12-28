package com.pk.home.library.library.parser;

public class ParserFactory {
    public Parser getParser(String fileType) {
        if (null == fileType) {
            return null;
        }
        if (fileType.equalsIgnoreCase("XML")) {
            return new BookToXMLParser();
        }
        if (fileType.equalsIgnoreCase("CSV")) {
            return new BookToCSVParser();
        }
        return null;
    }
}
