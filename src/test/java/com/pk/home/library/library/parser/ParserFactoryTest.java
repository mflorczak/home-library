package com.pk.home.library.library.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParserFactoryTest {

    private static ParserFactory PARSER_FACTORY = new ParserFactory();

    @Test
    void ThrowsExceptionWhenUnsupportedExtension() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> PARSER_FACTORY.getParser("asdasdad"));
    }

    @Test
    void GetParserShouldReturnXMLParserIfValidExtension() {
        Parser parser = PARSER_FACTORY.getParser("xMl");
        assertThat(parser instanceof BookToXMLParser);
    }

    @Test
    void GetParserShouldReturnCSVParserIfValidExtension() {
        Parser parser = PARSER_FACTORY.getParser("CsV");
        assertThat(parser instanceof BookToCSVParser);
    }


}