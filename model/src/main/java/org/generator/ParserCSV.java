package org.generator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.generator.entities.JPK;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ParserCSV {
    private WrapperJPK wrapperJPK_;

    private JPK jpk;

    public JPK getJpk() {
        return jpk;
    }
    private Map<String, JPK.Faktura> faktury_;
    private List<JPK.FakturaWiersz> fakturyRows_;
    public WrapperJPK getWrapperJPK_() {
        return wrapperJPK_;
    }
    Reader in;

    public ParserCSV(Reader in) {
        this.in = in;
        wrapperJPK_ = new WrapperJPK();
    }


    public void convert() throws IOException, DatatypeConfigurationException {
//        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter('\t').withHeader().parse(in);
        records.iterator().next();
        for (CSVRecord record : records) {
            System.out.println(record.size());

//            JPK.Faktura faktura = new JPK.Faktura();
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
//            BigDecimal kwotaPodatkuBigDecimal = toBigDecima(kwotaPodatku);
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
            //________________________________________________________
            wrapperJPK_.add(nrFaktury, dataWystawienia, dataSprzedazy, nazwaOdbiorcy, adresOdbiorcy, nipOdbiorcy, liczbaSztukBigDecimal, cenaBruttoPozycjiBigDecimal, cenaNettoPozycjiBigDecimal, cenaNettoFakturyBigDecimal, cenaBruttoFakturyBigDecimal, kwotaPodatku);
            jpk = wrapperJPK_.getJpk();
        }
    }


    public BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str.replaceAll("[^\\d,-]", "").replace(',', '.'));
    }




}
