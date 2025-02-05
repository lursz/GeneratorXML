package org.generator;

import org.generator.entities.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Wrapper class for JPK object
 */
public class WrapperJPK {
    private JPK jpk;

    public JPK getJpk() {
        return jpk;
    }

    private Map<String, JPK.Faktura> faktury_;
    private List<JPK.FakturaWiersz> fakturyRows_;

    /**
     * Constructor
     */
    WrapperJPK() {
        jpk = new JPK();
        jpk.setNaglowek(new JPK.Naglowek());
        jpk.getNaglowek().setKodFormularza(new TNaglowek.KodFormularza());
        jpk.setPodmiot1(new JPK.Podmiot1());
        jpk.setFakturaCtrl(new JPK.FakturaCtrl());
        jpk.getFakturaCtrl().setLiczbaFaktur(BigInteger.ZERO);
        jpk.getFakturaCtrl().setWartoscFaktur(BigDecimal.ZERO);

        jpk.setFakturaWierszCtrl(new JPK.FakturaWierszCtrl());
        jpk.getFakturaWierszCtrl().setWartoscWierszyFaktur(new BigDecimal("0"));
        jpk.getFakturaWierszCtrl().setLiczbaWierszyFaktur(new BigInteger("0"));
        faktury_ = new HashMap<>();
        fakturyRows_ = jpk.getFakturaWiersz();
    }

    /**
     * Function to initialize other functions
     * @return
     * @throws DatatypeConfigurationException
     */
    public WrapperJPK initialize() throws DatatypeConfigurationException {
        WrapperJPK wrapper_ = new WrapperJPK();
        wrapper_.Header();
        wrapper_.Subject();
        wrapper_.FakturaCtrl();
        wrapper_.FakturaRowCtrl();
        return wrapper_;
    }

    /**
     * Function to add record
     * @param invoiceNumber
     * @param dateOfIssue
     * @param dateOfPurchase
     * @param buyer
     * @param buyerAddress
     * @param buyerNIP
     * @param amount
     * @param unitPrice
     * @param rowNetPrice
     * @param netPrice
     * @param grossPrice
     * @param tax
     */
    public void add(String invoiceNumber, String dateOfIssue, String dateOfPurchase,
                    String buyer, String buyerAddress, String buyerNIP,
                    BigDecimal amount, BigDecimal unitPrice, BigDecimal rowNetPrice,
                    BigDecimal netPrice, BigDecimal grossPrice, String tax
    ) {
        newFakturaRow(invoiceNumber, amount, unitPrice, tax, rowNetPrice);
        newFaktura(invoiceNumber, dateOfIssue, dateOfPurchase, buyer, buyerAddress, buyerNIP, netPrice, grossPrice);
    }

    /**
     * Function to add header
     */
    void Header() {
        JPK.Naglowek naglowek_ = this.jpk.getNaglowek();
        TNaglowek.KodFormularza kod_formularza_ = naglowek_.getKodFormularza();
        kod_formularza_.setValue(TKodFormularza.fromValue("JPK_FA"));
        kod_formularza_.setKodSystemowy(kod_formularza_.getKodSystemowy());
        kod_formularza_.setWersjaSchemy(kod_formularza_.getWersjaSchemy());
        naglowek_.setWariantFormularza((byte) 3);
        naglowek_.setCelZlozenia((byte) 1);
//      naglowek_.setDataWytworzeniaJPK(currentTime());
        naglowek_.setKodUrzedu("1208");

    }

    /**
     * Function to add subject
     */
    void Subject() {
        JPK.Podmiot1 podmiot1_ = this.jpk.getPodmiot1();
        TIdentyfikatorOsobyNiefizycznej1 id = new TIdentyfikatorOsobyNiefizycznej1();
        id.setNIP("6762484560");
        id.setPelnaNazwa("\"CORE LOGIC\" SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ");
        podmiot1_.setIdentyfikatorPodmiotu(id);
        TAdresPolski1 podmiot_adres_ = new TAdresPolski1();
        podmiot_adres_.setKodKraju(TKodKraju.fromValue("PL"));
        podmiot_adres_.setWojewodztwo("małopolskie");
        podmiot_adres_.setPowiat("m. Kraków");
        podmiot_adres_.setGmina("Kraków");
        podmiot_adres_.setUlica("ul. Feliksa Radwańskiego");
        podmiot_adres_.setNrDomu("15");
        podmiot_adres_.setNrLokalu("1");
        podmiot_adres_.setMiejscowosc("Kraków");
        podmiot_adres_.setKodPocztowy("30-065");
        podmiot1_.setAdresPodmiotu(podmiot_adres_);
    }

    /**
     * Function to add invoice
     */
    private void FakturaCtrl() {
        JPK.FakturaCtrl invoiceCtrl = this.jpk.getFakturaCtrl();
        invoiceCtrl.setLiczbaFaktur(new BigInteger("0"));
        invoiceCtrl.setWartoscFaktur(new BigDecimal("0.00"));
    }

    /**
     * Function to add invoice row
     */
    private void FakturaRowCtrl() {
        JPK.FakturaWierszCtrl invoiceRowCtrl = this.jpk.getFakturaWierszCtrl();
        invoiceRowCtrl.setLiczbaWierszyFaktur(new BigInteger("0"));
        invoiceRowCtrl.setWartoscWierszyFaktur(new BigDecimal("0.00"));
    }

    /**
     * Function to update invoice row
     * @param i
     * @param d
     */
    private void updateFakturaCtrl(BigInteger i, BigDecimal d) {
        JPK.FakturaCtrl ctrl = this.jpk.getFakturaCtrl();
        ctrl.setLiczbaFaktur(ctrl.getLiczbaFaktur().add(i));
        ctrl.setWartoscFaktur(ctrl.getWartoscFaktur().add(d));
    }

    /**
     * Function to update invoice row
     * @param i
     * @param d
     */
    private void updateFakturaRowCtrl(BigInteger i, BigDecimal d) {
        JPK.FakturaWierszCtrl ctrl = this.jpk.getFakturaWierszCtrl();
        ctrl.setLiczbaWierszyFaktur(ctrl.getLiczbaWierszyFaktur().add(i));
        ctrl.setWartoscWierszyFaktur(ctrl.getWartoscWierszyFaktur().add(d));
    }

    /**
     * Function to add new invoice row
     * @param invoiceNumber
     * @param amount
     * @param unitPrice
     * @param tax
     * @param netPrice
     */
    public void newFakturaRow(String invoiceNumber, BigDecimal amount, BigDecimal unitPrice, String tax,
                              BigDecimal netPrice) {
        JPK.FakturaWiersz row = new JPK.FakturaWiersz();
        row.setP2B(invoiceNumber);
        row.setP7("Sprzedaż usług krajowych");
        row.setP8A("szt");
        row.setP8B(amount);
        row.setP9A(unitPrice);
        row.setP11(netPrice);
        row.setP12(tax);

        updateFakturaRowCtrl(BigInteger.ONE, netPrice);

        fakturyRows_.add(row);
    }

    /**
     * Function to add new invoice
     * @param invoiceNumber
     * @param dateOfIssue
     * @param dateOfPurchase
     * @param buyer
     * @param buyerAddress
     * @param buyerNIP
     * @param netPrice
     * @param grossPrice
     */
    private void newFaktura(String invoiceNumber, String dateOfIssue, String dateOfPurchase,
                            String buyer, String buyerAddress, String buyerNIP,
                            BigDecimal netPrice, BigDecimal grossPrice) {
        if (!faktury_.containsKey(invoiceNumber)) {
            JPK.Faktura invoice = new JPK.Faktura();
            invoice.setKodWaluty(CurrCodeType.PLN);
            invoice.setP2A(invoiceNumber);
            invoice.setP3A(buyer);
            invoice.setP3B(buyerAddress);
            invoice.setP3C("\"CORE LOGIC\" SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ");
            invoice.setP3D("ul. Feliksa Radwańskiego 15/1, 30-065 Kraków");
            invoice.setP4A(MSCountryCodeType.PL);
            invoice.setP4B("6762484560");
            invoice.setP5B(buyerNIP);
            invoice.setP131(netPrice);
            invoice.setP141(grossPrice.subtract(netPrice));
            invoice.setP15(grossPrice);

            try {
                invoice.setP1(toXMLGregorian(dateOfIssue));
                updateDates(toXMLGregorian(dateOfIssue));
            } catch (DatatypeConfigurationException ignored) {
            }

            try {
                invoice.setP6(toXMLGregorian(dateOfPurchase));
                updateDates(toXMLGregorian(dateOfPurchase));
            } catch (DatatypeConfigurationException ignored) {
            }

            updateFakturaCtrl(BigInteger.ONE, grossPrice);

            faktury_.put(invoiceNumber, invoice);

        }
    }

    /**
     * Function to convert string to BigDecimal
     * @param str
     * @return
     */
    public BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str.replaceAll("[^\\d,-]", "").replace(',', '.'));
    }

    /**
     * Function to convert string to XMLGregorian
     * @param str
     * @return
     * @throws DatatypeConfigurationException
     */
    public XMLGregorianCalendar toXMLGregorian(String str) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(str);
    }

    /**
     * Function to update dates
     * @param date
     */
    private void updateDates(XMLGregorianCalendar date) {
        JPK.Naglowek header = jpk.getNaglowek();
        if (header.getDataOd() == null || header.getDataOd().compare(date) == DatatypeConstants.LESSER) {
            header.setDataOd(date);
        }
        if (header.getDataDo() == null || header.getDataDo().compare(date) == DatatypeConstants.GREATER) {
            header.setDataDo(date);
        }
    }

    /**
     * Function to build JPK
     * @return
     */
    public JPK build() {
        jpk.getFaktura().addAll(faktury_.values());
        return jpk;
    }

}
