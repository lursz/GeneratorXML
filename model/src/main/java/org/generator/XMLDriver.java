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

/**
 * Class to generate final XML file using received JPK object
 */
public class XMLDriver {
   private JPK jpk = new JPK();
   private String output;

    public XMLDriver(JPK jpk, String output) {
        this.jpk = jpk;
        this.output = output;
    }

    /**
     * Marshalling method to generate XML file
     */
    public void createXML()
    {
        try
        {
            JAXBContext jc = JAXBContext.newInstance(org.generator.entities.JPK.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream os = Files.newOutputStream(Paths.get(output));
            m.marshal( jpk, os );

        }
        catch (JAXBException | IOException e)
        {
            e.printStackTrace();
        }
    }

}
