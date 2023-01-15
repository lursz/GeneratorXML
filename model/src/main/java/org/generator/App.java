package org.generator;

import org.generator.entities.JPK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;

public class App {
    public static void main(String[] args) {
        //Check for arguments
        if (args.length < 2) {
            System.out.println("Invalid command");
            return;
        }
        String input = args[0];
        String output = args[1];

        JPK jpk = new JPK();
        //Process inserted file
        try {
            if (input.endsWith(".csv")) {
                Reader file_in = new FileReader(input);
                ParserCSV parserCSV = new ParserCSV(file_in);
                parserCSV.convert();
                jpk = parserCSV.getJpk();


            } else if (input.endsWith(".xlsx")) {
                FileInputStream file_in = new FileInputStream(new File(input));

                ParserXLSX parserXLSX = new ParserXLSX(file_in);

            } else {
                System.out.println("Invalid file type");
                return;
            }
            //TODO
            XMLDriver xmlDriver = new XMLDriver(jpk);
            xmlDriver.createXML();

        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}

