package com.magicmover.xmlwritemanager;

import com.magicmover.models.Occurrence;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by avismarahl on 28/06/17.
 */
public class WriteManager {
    private Map<String,String> xmlValues = new HashMap<>();
    String originalCode = null;
    public WriteManager(String originalCode) {
        this.originalCode = originalCode;
    }
    public void writeToXML(List<Occurrence> occurrences) {
        for (Occurrence occurrence : occurrences) {
            Integer key = occurrence.getPosition();
            String value = occurrence.getString();
            String lowerCaseValue = value.toLowerCase();
            String formattedString = value.replace(" ","_");
            formattedString = formattedString.replace("\"","");
            String replaceString = "getString(R.string." + formattedString + ")";
            originalCode = originalCode.replace(value,replaceString);
            if(!xmlValues.containsKey(lowerCaseValue)) {
                xmlValues.put(lowerCaseValue,formattedString);
            }
        }
        System.out.println(originalCode);
    }

    private void createXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Hardcoded strings to follow to laugh at the face of irony
            Element rootElement = doc.createElement("string");
            rootElement.appendChild(rootElement);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
