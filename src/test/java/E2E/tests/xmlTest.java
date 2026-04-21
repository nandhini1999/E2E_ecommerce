package E2E.tests;
import E2E.utils.XMLReader;
import E2E.utils.XMLValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

public class xmlTest {

    @Test
    public void xmlSchemaValidate() throws IOException, SAXException {
        String xmlFile = System.getProperty("user.dir")+"\\src\\test\\java\\E2E\\Data\\test.xml";
        String xmlSchema = System.getProperty("user.dir")+"\\src\\main\\java\\E2E\\resources\\xmlSchema.xsd";
        XMLValidator.xmlValidator(xmlFile,xmlSchema);
    }

    @Test
    public void XMLValue() throws ParserConfigurationException, IOException, SAXException {
       NodeList invoices = XMLReader.xmlReader("invoice");

       for(int i=0;i<invoices.getLength();i++)
       {
         Node node =  invoices.item(i);
         Element invoice = (Element) node;
         String id = invoice.getElementsByTagName("id").item(0).getTextContent();
         System.out.println(id);
       }
    }

    @Test
    public void getXMLValueByXpath() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
       String value = XMLReader.xPath("//invoices/invoice[2]/amount");
       double amount = Double.parseDouble(value);
       System.out.println(amount);
    }

        }
