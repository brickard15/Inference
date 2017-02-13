package edu.murraystate.csis.inference.adapters.documents.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class XmlDocumentAdapterTest {

    @Test
    public void simpleXMLEventReaderTest() throws FileNotFoundException, IOException, XMLStreamException{
        File tempXMLFile = File.createTempFile("Prefix", "Suffix");
        PrintWriter XMLWriter = new PrintWriter(tempXMLFile);
        XMLWriter.println("<name>John</name>");
        XMLWriter.close();  
        Reader xmlFileReader = new FileReader(tempXMLFile);
        String resultText = "false";
        
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(xmlFileReader);
        
        while(eventReader.hasNext()){
            XMLEvent nextEvent = eventReader.nextEvent();
            if(nextEvent.isCharacters()){
                resultText = nextEvent.toString();
            }
        }
        
        assertEquals("John", resultText);
    }
}

