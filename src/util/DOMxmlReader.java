package util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DOMxmlReader {
    private Map<String, BigDecimal> currenciesMap = new HashMap<>();

    public void read(String filepath) {
        String date = "";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        System.out.println("Reading file of currencies...");
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Cube");

            Map<String, BigDecimal> currMap = new HashMap<>();
            String curr;
            BigDecimal value;
            date = nodeList.item(1).getAttributes().getNamedItem("time").getNodeValue();
            for (int i = 2; i < nodeList.getLength(); i++) {
                curr = nodeList.item(i).getAttributes().getNamedItem("currency").getNodeValue();
                value = new BigDecimal(nodeList.item(i).getAttributes().getNamedItem("rate").getNodeValue());
                currMap.put(curr, value);
            }
            this.currenciesMap = currMap;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Great! File by date " + date + " was successful read.");
    }

    public Map<String, BigDecimal> getCurrenciesMap() {
        return currenciesMap;
    }
}
