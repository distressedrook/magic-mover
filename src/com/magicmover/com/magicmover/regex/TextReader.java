package com.magicmover.com.magicmover.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    private static final String FILENAME = "D:\\test\\filename.txt";

    public static String getTextFromFile(String FILENAME) {
        BufferedReader br = null;
        FileReader fr = null;

        String strings = " ";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(FILENAME));
            while ((sCurrentLine = br.readLine()) != null) {
                strings += sCurrentLine+ " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return strings;
    }
    public static void main(String[] arg){
        System.out.print(getTextFromFile("D:\\test\\filename.txt"));
    }
}