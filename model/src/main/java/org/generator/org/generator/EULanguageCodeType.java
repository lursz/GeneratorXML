//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.11 at 05:42:19 PM CET 
//


package org.generator;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EULanguageCode_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="EULanguageCode_Type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="bg"/&gt;
 *     &lt;enumeration value="cs"/&gt;
 *     &lt;enumeration value="da"/&gt;
 *     &lt;enumeration value="de"/&gt;
 *     &lt;enumeration value="el"/&gt;
 *     &lt;enumeration value="en"/&gt;
 *     &lt;enumeration value="es"/&gt;
 *     &lt;enumeration value="et"/&gt;
 *     &lt;enumeration value="fi"/&gt;
 *     &lt;enumeration value="fr"/&gt;
 *     &lt;enumeration value="ga"/&gt;
 *     &lt;enumeration value="hr"/&gt;
 *     &lt;enumeration value="hu"/&gt;
 *     &lt;enumeration value="it"/&gt;
 *     &lt;enumeration value="lt"/&gt;
 *     &lt;enumeration value="lv"/&gt;
 *     &lt;enumeration value="mt"/&gt;
 *     &lt;enumeration value="nl"/&gt;
 *     &lt;enumeration value="pl"/&gt;
 *     &lt;enumeration value="pt"/&gt;
 *     &lt;enumeration value="ro"/&gt;
 *     &lt;enumeration value="sk"/&gt;
 *     &lt;enumeration value="sl"/&gt;
 *     &lt;enumeration value="sv"/&gt;
 *     &lt;enumeration value="tr"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EULanguageCode_Type", namespace = "http://crd.gov.pl/xml/schematy/dziedzinowe/mf/2013/05/23/eD/KodyCECHKRAJOW/")
@XmlEnum
public enum EULanguageCodeType {


    /**
     * Bulgarian
     * 
     */
    @XmlEnumValue("bg")
    BG("bg"),

    /**
     * Czech
     * 
     */
    @XmlEnumValue("cs")
    CS("cs"),

    /**
     * Danish
     * 
     */
    @XmlEnumValue("da")
    DA("da"),

    /**
     * German
     * 
     */
    @XmlEnumValue("de")
    DE("de"),

    /**
     * Greek
     * 
     */
    @XmlEnumValue("el")
    EL("el"),

    /**
     * English
     * 
     */
    @XmlEnumValue("en")
    EN("en"),

    /**
     * Spanish
     * 
     */
    @XmlEnumValue("es")
    ES("es"),

    /**
     * Estonian
     * 
     */
    @XmlEnumValue("et")
    ET("et"),

    /**
     * Finnish
     * 
     */
    @XmlEnumValue("fi")
    FI("fi"),

    /**
     * French
     * 
     */
    @XmlEnumValue("fr")
    FR("fr"),

    /**
     * Irish
     * 
     */
    @XmlEnumValue("ga")
    GA("ga"),

    /**
     * Croatian
     * 
     */
    @XmlEnumValue("hr")
    HR("hr"),

    /**
     * Hungarian
     * 
     */
    @XmlEnumValue("hu")
    HU("hu"),

    /**
     * Italian
     * 
     */
    @XmlEnumValue("it")
    IT("it"),

    /**
     * Lithuanian
     * 
     */
    @XmlEnumValue("lt")
    LT("lt"),

    /**
     * Latvian
     * 
     */
    @XmlEnumValue("lv")
    LV("lv"),

    /**
     * Maltese
     * 
     */
    @XmlEnumValue("mt")
    MT("mt"),

    /**
     * Dutch
     * 
     */
    @XmlEnumValue("nl")
    NL("nl"),

    /**
     * Polish
     * 
     */
    @XmlEnumValue("pl")
    PL("pl"),

    /**
     * Portuguese
     * 
     */
    @XmlEnumValue("pt")
    PT("pt"),

    /**
     * Romanian
     * 
     */
    @XmlEnumValue("ro")
    RO("ro"),

    /**
     * Slovak 
     * 
     */
    @XmlEnumValue("sk")
    SK("sk"),

    /**
     * Slovenian 
     * 
     */
    @XmlEnumValue("sl")
    SL("sl"),

    /**
     * Swedish
     * 
     */
    @XmlEnumValue("sv")
    SV("sv"),

    /**
     * Turkish
     * 
     */
    @XmlEnumValue("tr")
    TR("tr");
    private final String value;

    EULanguageCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EULanguageCodeType fromValue(String v) {
        for (EULanguageCodeType c: EULanguageCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
