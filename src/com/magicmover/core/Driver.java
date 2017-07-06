package com.magicmover.core;

import com.magicmover.models.Occurrence;
import com.magicmover.regex.RegexParser;
import com.magicmover.regex.TextReader;
import com.magicmover.xmlwritemanager.WriteManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by avismarahl on 06/07/17.
 */
public class Driver {
    RegexParser regexParser = new RegexParser();
    WriteManager writeManager = new WriteManager();
    TextReader textReader = new TextReader();

    public void traverseDirectory(File topFile) {
        File[] files = topFile.listFiles();
        if(files.length == 0) {
            return;
        }
        for(File file : files) {
            if(file.isDirectory()) {
                traverseDirectory(file);
            } else {
                if(!file.getAbsolutePath().equalsIgnoreCase("/Users/avismarahl/Development/betcade-android/app/src/main/java/com/betcade/unused/uervice/client/ServiceGenerator.java")) {
                    System.out.println("XML generated for : " + file.getAbsolutePath());
                    generateXML(file.getAbsolutePath());
                }


            }
        }

    }

    private void generateXML(String absolutePath) {
        try {
            String originalCode = textReader.readFile(absolutePath, Charset.defaultCharset());
            List<Occurrence> occurrences = regexParser.parseSourceCode(originalCode);
            writeManager.setOriginalCode(originalCode);
            writeManager.writeToXML(occurrences);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void convert() {
        TextReader textReader = new TextReader();
        File file = new File("/Users/avismarahl/Development/betcade-android/app/src/main/java/com/");
        traverseDirectory(file);
        WriteManager.writeXML();
    }
}
