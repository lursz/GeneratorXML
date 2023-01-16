package org.generator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParserCSVTest {

    @Test
    public void testToBigDecimal() throws IOException {
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("123.45"));
        assertEquals(new BigDecimal("-12345"), ParserXLSX.toBigDecimal("-123.45"));
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("$123.45"));
        assertEquals(new BigDecimal("123.45"), ParserXLSX.toBigDecimal("123,45"));
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("($123.45)"));
    }
}