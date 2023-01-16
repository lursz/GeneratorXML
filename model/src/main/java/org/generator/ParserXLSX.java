package org.generator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.generator.entities.JPK;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ParserXLSX {
    private JPK jpk = new JPK();
    private WrapperJPK wrapperJPK_ = new WrapperJPK();
    private XSSFWorkbook workbook;


    public ParserXLSX(FileInputStream file_in) throws IOException {
        this.workbook = new XSSFWorkbook(file_in);
        ;
    }

    public JPK getJpk() {
        return jpk;
    }

    //=================================

    public void convert() {
        try {
//            FileInputStream file = new FileInputStream(filePath);
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;

            for (Row row : sheet) {
                if(rowCount == 0) {
                    rowCount++;
                    continue;
                }
                //Nazwa odbiorcy
                String nazwaOdbiorcy = row.getCell(0).getStringCellValue();
                //Adres odbiorcy
                String adresOdbiorcy = row.getCell(1).getStringCellValue();
                //NIP odbiorcy
                String nipOdbiorcy = row.getCell(2).getStringCellValue();
                //Data wystawienia
//                String dataWystawienia = row.getCell(3).getStringCellValue();
                String dataWystawienia = dateToString(row.getCell(3).getDateCellValue());
                //Data sprzedaży
//                String dataSprzedazy = row.getCell(4).getStringCellValue();
                String dataSprzedazy = dateToString(row.getCell(4).getDateCellValue());
                //Numer faktury
                String nrFaktury = row.getCell(5).getStringCellValue();
                //Cena jednostkowa
                String cenaJednostkowa = row.getCell(8).getStringCellValue();
//                BigDecimal cenaJednostkowaBigDecimal = toBigDecimal(cenaJednostkowa);
                //Stawka podatku
                String stawkaPodatku = row.getCell(9).getStringCellValue();
//                BigDecimal stawkaPodatkuBigDecimal = toBigDecimal(stawkaPodatku);
                //Kwota podatku
                String kwotaPodatku = row.getCell(10).getStringCellValue().replaceAll("[^\\d,-]", "").replace(',', '.');
//                BigDecimal kwotaPodatkuBigDecimal = toBigDecimal(kwotaPodatku);
                //Liczba sztuk
                String liczbaSztuk = row.getCell(7).getStringCellValue();
                BigDecimal liczbaSztukBigDecimal = toBigDecimal(liczbaSztuk);
                //Tytul pozycji
                String tytulPozycji = row.getCell(6).getStringCellValue();
                //Kwota netto
                String cenaNettoFaktury = row.getCell(13).getStringCellValue();
                BigDecimal cenaNettoFakturyBigDecimal = toBigDecimal(cenaNettoFaktury);
                //Kwota brutto
                String cenaBruttoFaktury = row.getCell(14).getStringCellValue();
                BigDecimal cenaBruttoFakturyBigDecimal = toBigDecimal(cenaBruttoFaktury);
                //Cena netto pozycji
                String cenaNettoPozycji = row.getCell(11).getStringCellValue();
                BigDecimal cenaNettoPozycjiBigDecimal = toBigDecimal(cenaNettoPozycji);
                //Cena brutto pozycji
                String cenaBruttoPozycji = row.getCell(12).getStringCellValue();
                BigDecimal cenaBruttoPozycjiBigDecimal = toBigDecimal(cenaBruttoPozycji);
                rowCount++;
                wrapperJPK_.initialize();
                wrapperJPK_.build();
                wrapperJPK_.add(nrFaktury, dataWystawienia, dataSprzedazy, nazwaOdbiorcy, adresOdbiorcy, nipOdbiorcy, liczbaSztukBigDecimal, cenaBruttoPozycjiBigDecimal, cenaNettoPozycjiBigDecimal, cenaNettoFakturyBigDecimal, cenaBruttoFakturyBigDecimal, stawkaPodatku);
                jpk = wrapperJPK_.getJpk();

            }

        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }

    }


    public BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str.replaceAll("[^\\d,-]", "").replace(',', '.'));
    }


    public String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
