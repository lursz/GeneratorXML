package org.generator;

import org.generator.entities.JPK;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XMLDriver {
   JPK jpk = new JPK();

    public XMLDriver(JPK jpk) {
        this.jpk = jpk;
    }

    public void createXML()
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(JPK.class);
            Marshaller m = jaxbContext.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File file = new File("jpk.xml");

            //Writes XML file to file-system
            m.marshal(jpk, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

}
