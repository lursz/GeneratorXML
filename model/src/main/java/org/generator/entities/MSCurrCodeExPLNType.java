//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.16 at 01:55:05 PM CET 
//


package org.generator.entities;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MSCurrCodeExPLN_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="MSCurrCodeExPLN_Type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BGN"/&gt;
 *     &lt;enumeration value="CZK"/&gt;
 *     &lt;enumeration value="DKK"/&gt;
 *     &lt;enumeration value="EEK"/&gt;
 *     &lt;enumeration value="EUR"/&gt;
 *     &lt;enumeration value="GBP"/&gt;
 *     &lt;enumeration value="HRK"/&gt;
 *     &lt;enumeration value="HUF"/&gt;
 *     &lt;enumeration value="LTL"/&gt;
 *     &lt;enumeration value="LVL"/&gt;
 *     &lt;enumeration value="RON"/&gt;
 *     &lt;enumeration value="SEK"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MSCurrCodeExPLN_Type", namespace = "http://crd.gov.pl/xml/schematy/dziedzinowe/mf/2013/05/23/eD/KodyCECHKRAJOW/")
@XmlEnum
public enum MSCurrCodeExPLNType {

    BGN,
    CZK,
    DKK,
    EEK,
    EUR,
    GBP,
    HRK,
    HUF,
    LTL,
    LVL,
    RON,
    SEK;

    public String value() {
        return name();
    }

    public static MSCurrCodeExPLNType fromValue(String v) {
        return valueOf(v);
    }

}
