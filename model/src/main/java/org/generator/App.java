package org.generator;

import org.generator.entities.JPK;

public class App {
    public static void main(String[] args) {
        JPK jpk = new JPK();
        ParserCSV parserCSV = new ParserCSV(jpk);
        try {
            parserCSV.readAndCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parserCSV.jaxbObjectToXML();



    }
}





























//        JAXBContext context = JAXBContext.newInstance(Wydzial.class);
//        Marshaller mar = context.createMarshaller();
//        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        mar.marshal(wydzial, new File("./wydzial.xml"));