package org.generator;

import org.generator.entities.JPK;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLDriver {
   JPK jpk = new JPK();

    public XMLDriver(JPK jpk) {
        this.jpk = jpk;
    }

    public void createXML()
    {
        try
        {
            JAXBContext jc = JAXBContext.newInstance(org.generator.entities.JPK.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream os = Files.newOutputStream(Paths.get("jpk.xml"));
            m.marshal( jpk, os );

            //Create JAXB Context
//            JAXBContext jaxbContext = JAXBContext.newInstance(JPK.class);
//            Marshaller m = jaxbContext.createMarshaller();
//
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            //Store XML to File
//            File file = new File("jpk.xml");
//
//            //Writes XML file to file-system
//            m.marshal(jpk, file);

        }
        catch (JAXBException | IOException e)
        {
            e.printStackTrace();
        }
    }

}
