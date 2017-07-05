package com.magicmover;

import com.magicmover.com.magicmover.regex.RegexParser;
import com.magicmover.com.magicmover.regex.TextReader;
import com.magicmover.models.Occurrence;
import com.magicmover.xmlwritemanager.WriteManager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReader();
        try {
            String originalCode = textReader.readFile("/Users/avismarahl/Development/betcade-android/app/src/main/java/com/betcade/activity/ActAllCategoryList.java", Charset.defaultCharset());
            //String originalCode = textReader.readFile("/Users/avismarahl/Desktop/city.list.json",Charset.defaultCharset());
            RegexParser regexParser = new RegexParser();
            List<Occurrence> occurrences = regexParser.parseSourceCode(originalCode);
            WriteManager writeManager = new WriteManager(originalCode);
            writeManager.writeToXML(occurrences);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
