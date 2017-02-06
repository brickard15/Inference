package edu.murraystate.csis.inference.adapters.documents.xml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlDocumentAdapterTest {
    
    @Test
    public void simpleTest() throws IOException, SAXException, ParserConfigurationException{
        File tempXMLFile = File.createTempFile("Prefix", "Suffix");
        PrintWriter XMLWriter = new PrintWriter(tempXMLFile);
        XMLWriter.println("<name>John</name>");
        XMLWriter.close();
                
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(tempXMLFile);
        
        Node documentNode = document.getFirstChild();
        String textContent = documentNode.getTextContent();
        
        assertEquals("John", textContent);
        
    }
}
