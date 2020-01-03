package com.pk.home.library.library.parser;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserFactoryTest {
    private static ParserFactory PARSER_FACTORY = new ParserFactory();

    @Test
    public void ThrowExceptionWhenUnsupportedExtension() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> PARSER_FACTORY.getParser("joasdnjiuoasn"));
    }

    @Test
    public void ParserShouldReturnXMLParserIfValidExtension() {
        Parser parser = PARSER_FACTORY.getParser("XML");
        assertThat(parser instanceof BookToXMLParser);
    }

    @Test
    public void ParserShouldReturnCSVParserIfValidExtension() {
        Parser parser = PARSER_FACTORY.getParser("CSV");
        assertThat(parser instanceof BookToCSVParser);
    }
}