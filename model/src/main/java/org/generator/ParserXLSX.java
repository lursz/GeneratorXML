package org.generator;

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

/**
 * Parser class for XLSX files
 */
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

    /**
     * Function to convert XLSX file to JPK object using wrapperJPK
     */
    public void convert() {
        try {
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
                String dataWystawienia = dateToString(row.getCell(3).getDateCellValue());
                //Data sprzedaży
                String dataSprzedazy = dateToString(row.getCell(4).getDateCellValue());
                //Numer faktury
                String nrFaktury = row.getCell(5).getStringCellValue();
                //Cena jednostkowa
                String cenaJednostkowa = String.valueOf(row.getCell(8).getNumericCellValue());
                //Stawka podatku
                String stawkaPodatku =  String.valueOf(row.getCell(9).getNumericCellValue());
                //Kwota podatku
                String kwotaPodatku =  String.valueOf(row.getCell(10).getNumericCellValue());
                //Liczba sztuk
                String liczbaSztuk =  String.valueOf(row.getCell(7).getNumericCellValue());
                BigDecimal liczbaSztukBigDecimal = toBigDecimal(liczbaSztuk);
                //Tytul pozycji
                String tytulPozycji = row.getCell(6).getStringCellValue();
                //Kwota netto
                String cenaNettoFaktury =  String.valueOf(row.getCell(13).getNumericCellValue());
                BigDecimal cenaNettoFakturyBigDecimal = toBigDecimal(cenaNettoFaktury);
                //Kwota brutto
                String cenaBruttoFaktury =  String.valueOf(row.getCell(14).getNumericCellValue());
                BigDecimal cenaBruttoFakturyBigDecimal = toBigDecimal(cenaBruttoFaktury);
                //Cena netto pozycji
                String cenaNettoPozycji =  String.valueOf(row.getCell(11).getNumericCellValue());
                BigDecimal cenaNettoPozycjiBigDecimal = toBigDecimal(cenaNettoPozycji);
                //Cena brutto pozycji
                String cenaBruttoPozycji =  String.valueOf(row.getCell(12).getNumericCellValue());
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

    /**
     * Function to convert String to BigDecimal
     * @param str
     * @return
     */
    static public BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str.replaceAll("[^\\d,-]", "").replace(',', '.'));
    }

    /**
     * Function to convert Double to String
     * @param d
     * @return
     */
    static public String doubleToString(Double d) {
        return String.valueOf(d);
    }

    /**
     * Function to convert Date to String
     * @param date
     * @return
     */
    static public String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
