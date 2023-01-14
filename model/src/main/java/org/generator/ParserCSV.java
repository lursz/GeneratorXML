package org.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.generator.entities.JPK;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;

public class ParserCSV {
    WrapperJPK wrapperJPK_;
    public WrapperJPK getWrapperJPK_() {
        return wrapperJPK_;
    }
    Reader in;

    public ParserCSV(Reader in) {
        this.in = in;
        wrapperJPK_ = new WrapperJPK();
    }



    //========================================================

    public void convert() throws IOException, DatatypeConfigurationException {
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {


        }
    }







}
