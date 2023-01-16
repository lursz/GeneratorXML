package org.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.generator.entities.JPK;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;

/**
 * Parser class for CSV files
 */
public class ParserCSV {
    Reader in;
    private WrapperJPK wrapperJPK_;
    private JPK jpk;

    public JPK getJpk() {
        return jpk;
    }

    public ParserCSV(Reader in) {
        this.in = in;
        wrapperJPK_ = new WrapperJPK();
    }

    /**
     * Function to convert CSV file to JPK object using wrapperJPK
     * @throws IOException
     * @throws DatatypeConfigurationException
     */
    public void convert() throws IOException, DatatypeConfigurationException {
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter('\t').withHeader().parse(in);
        records.iterator().next();
        for (CSVRecord record : records) {
            //Nazwa odbiorcy
            String nazwaOdbiorcy = record.get(0);
            //Adres odbiorcy
            String adresOdbiorcy = record.get(1);
            //NIP odbiorcy
            String nipOdbiorcy = record.get(2);
            //Data wystawienia
            String dataWystawienia = record.get(3);
            //Data sprzedaży
            String dataSprzedazy = record.get(4);
            //Numer faktury
            String nrFaktury = record.get(5);
            //Cena jednostkowa
            String cenaJednostkowa = record.get(8);
            BigDecimal cenaJednostkowaBigDecimal = toBigDecimal(cenaJednostkowa);
            //Stawka podatku
            String stawkaPodatku = record.get(9);
            BigDecimal stawkaPodatkuBigDecimal = new BigDecimal(stawkaPodatku);
            //Kwota podatku
            String kwotaPodatku = record.get(10).replaceAll("[^\\d,-]", "").replace(',', '.');
            //Liczba sztuk
            String liczbaSztuk = record.get(7);
            BigDecimal liczbaSztukBigDecimal = toBigDecimal(liczbaSztuk);
            //Tytul pozycji
            String tytulPozycji = record.get(6);
            //Kwota netto
            String cenaNettoFaktury = record.get(13);
            BigDecimal cenaNettoFakturyBigDecimal = toBigDecimal(cenaNettoFaktury);
            //Kwota brutto
            String cenaBruttoFaktury = record.get(14);
            BigDecimal cenaBruttoFakturyBigDecimal = toBigDecimal(cenaBruttoFaktury);
            //Cena netto pozycji
            String cenaNettoPozycji = record.get(11);
            BigDecimal cenaNettoPozycjiBigDecimal = toBigDecimal(cenaNettoPozycji);
            //Cena brutto pozycji
            String cenaBruttoPozycji = record.get(12);
            BigDecimal cenaBruttoPozycjiBigDecimal = toBigDecimal(cenaBruttoPozycji);

            wrapperJPK_.initialize();
            wrapperJPK_.build();
            wrapperJPK_.add(nrFaktury, dataWystawienia, dataSprzedazy, nazwaOdbiorcy, adresOdbiorcy, nipOdbiorcy, liczbaSztukBigDecimal, cenaBruttoPozycjiBigDecimal, cenaNettoPozycjiBigDecimal, cenaNettoFakturyBigDecimal, cenaBruttoFakturyBigDecimal, stawkaPodatku);
            jpk = wrapperJPK_.getJpk();
        }
    }

    /**
     * Function to convert String to BigDecimal
     * @param str
     * @return
     */
    static public BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str.replaceAll("[^\\d,-]", "").replace(',', '.'));
    }


}
