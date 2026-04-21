package E2E.utils;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    public static void xmlValidator(String xmlFile,String xmlSchema) throws SAXException, IOException {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xmlSchema));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));

            System.out.println("XML is valid");
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
