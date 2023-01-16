package org.generator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ParserXLSXTest {




    @Test
    public void testToBigDecimal() throws IOException {
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("123.45"));
        assertEquals(new BigDecimal("-12345"), ParserXLSX.toBigDecimal("-123.45"));
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("$123.45"));
        assertEquals(new BigDecimal("123.45"), ParserXLSX.toBigDecimal("123,45"));
        assertEquals(new BigDecimal("12345"), ParserXLSX.toBigDecimal("($123.45)"));
    }


    @Test
    public void testDoubleToString() {
        assertEquals("1.0", ParserXLSX.doubleToString(1.0));
        assertEquals("-1.5", ParserXLSX.doubleToString(-1.5));
        assertEquals("0.0", ParserXLSX.doubleToString(0.0));
        assertEquals("1000000.0", ParserXLSX.doubleToString(1000000.0));
        assertEquals("-1000000.0", ParserXLSX.doubleToString(-1000000.0));
    }

    @Test
    public void testDateToString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDate = sdf.format(date);
        String actualDate = ParserXLSX.dateToString(date);
        assertEquals(expectedDate, actualDate);

        date = new Date(1234567890);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        expectedDate = sdf.format(date);
        actualDate = ParserXLSX.dateToString(date);
        assertEquals(expectedDate, actualDate);
    }
}