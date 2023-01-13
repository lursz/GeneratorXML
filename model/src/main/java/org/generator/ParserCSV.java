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
    JPK jpk = new JPK();
    Reader in;

    public ParserCSV(Reader in) {
        this.in = in;
    }

    public JPK getJpk() {
        return jpk;
    }

    //========================================================

    public void convert() throws IOException, DatatypeConfigurationException {
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            JPK.Faktura faktura = new JPK.Faktura();
            //Nazwa odbiorcy
            String nazwaOdbiorcy = record.get("Nazwa odbiorcy");
            faktura.setP3A(nazwaOdbiorcy);
            //Adres odbiorcy
            String adresOdbiorcy = record.get("Adres odbiorcy");
            faktura.setP3B(adresOdbiorcy);
            //NIP odbiorcy
            String nipOdbiorcy = record.get("NIP odbiorcy");
            faktura.setP5B(nipOdbiorcy);
            //Data wystawienia
            String dataWystawienia = record.get("Data wystawienia");
            XMLGregorianCalendar dataWystawieniaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(dataWystawienia);
            faktura.setP1(dataWystawieniaXML);
            //Data sprzedaży
            String dataSprzedazy = record.get("Data sprzedaży");
            XMLGregorianCalendar dataSprzedazyXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(dataSprzedazy);
            faktura.setP6(dataSprzedazyXML);
            //Numer faktury
            String nrFaktury = record.get("Nr faktury");
            faktura.setP2A(nrFaktury);
            //Cena jednostkowa
            String cenaJednostkowa = record.get("Cena jednostkowa");
            BigDecimal cenaJednostkowaBigDecimal = new BigDecimal(cenaJednostkowa);
            faktura.setP131(cenaJednostkowaBigDecimal);
            //Kwota podatku
            String kwotaPodatku = record.get("Kwota Podatku");
            BigDecimal kwotaPodatkuBigDecimal = new BigDecimal(kwotaPodatku);
            faktura.setP141(kwotaPodatkuBigDecimal);
            //Kwota netto
            String cenaNettoFaktury = record.get("Cena netto faktury łącznie");
            BigDecimal cenaNettoFakturyBigDecimal = new BigDecimal(cenaNettoFaktury);
            faktura.setP131(cenaNettoFakturyBigDecimal);
            //Kwota brutto
            String cenaBruttoFaktury = record.get("Cena brutto faktury łącznie");
            BigDecimal cenaBruttoFakturyBigDecimal = new BigDecimal(cenaBruttoFaktury);
            faktura.setP15(cenaBruttoFakturyBigDecimal);


            //________________________________________________________
            String stawkaPodatku = record.get("Stawka podatku %");
//            faktura.setStawkaPodatku(stawkaPodatku);
            String tytulPozycji = record.get("Tytuł pozycji");
//            faktura.setCOS(tytulPozycji);
            String liczbaSztuk = record.get("Liczba sztuk");
//            faktura.setP8B(liczbaSztuk);
            String cenaNettoPozycji = record.get("Cena netto pozycji");
//            faktura.setCenaNettoPozycji(cenaNettoPozycji);
            String cenaBruttoPozycji = record.get("Cena brutto pozycji");
//            faktura.setCenaBruttoPozycji(cenaBruttoPozycji);

        }
    }







}
