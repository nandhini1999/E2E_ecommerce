package E2E.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class XMLReader {

    public static NodeList xmlReader(String nodeName) throws ParserConfigurationException, IOException, SAXException {

        String path = System.getProperty("user.dir")+"\\src\\test\\java\\E2E\\Data\\test.xml";
        //Create instance from factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //factory -> new builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //parse the file -> document
        Document document =   builder.parse(path);
        document.getDocumentElement().normalize();

       NodeList invoices = document.getElementsByTagName(nodeName);
        return invoices;

    }

    public static String xPath(String xpathValue) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        String filepath = System.getProperty("user.dir")+"\\src\\test\\java\\E2E\\Data\\test.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filepath);

        XPath xpath = XPathFactory.newInstance().newXPath();
       return xpath.evaluate(xpathValue,document);
    }

}
