package com.magicmover.xmlwritemanager;

import com.magicmover.models.Occurrence;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by avismarahl on 28/06/17.
 */
public class WriteManager {
    private static Map<String,String> xmlValues = new HashMap<>();
    private String originalCode = null;

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    static Document document = null;
    static Element rootElement = null;
    public void writeToXML(List<Occurrence> occurrences) {
        for (Occurrence occurrence : occurrences) {
            String value = occurrence.getString();
            String lowerCaseValue = value.toLowerCase();
            String formattedString = lowerCaseValue.replace(" ","_");
            formattedString = formattedString.replace("\"","");
            String replaceString = "getString(R.string." + formattedString + ")";
            originalCode = originalCode.replace(value,replaceString);
            if(!xmlValues.containsKey(lowerCaseValue)) {
                xmlValues.put(lowerCaseValue,formattedString);
                populateXML(formattedString,value.substring(1, value.length()-1));
            }
        }
    }


    public static void writeXML() {
        System.out.println("Writing to xml...");
        try {
            // write the content into xml file
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result =
                    new StreamResult(new File("/Users/avismarahl/Desktop/strings.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult =
                    new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void populateXML(String key, String value) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            //Hardcoded strings to follow to laugh at the face of irony
            if(this.document == null) {
                document = dBuilder.newDocument();
                Element rootElement = document.createElement("resources");
                document.appendChild(rootElement);
                this.rootElement = rootElement;
            }
            Element stringElement = document.createElement("string");
            Attr attr = document.createAttribute("name");
            attr.setValue(key);
            stringElement.setAttributeNode(attr);
            stringElement.appendChild(document.createTextNode(value));
            rootElement.appendChild(stringElement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
