package edu.murraystate.csis.inference.adapters.documents.xml;

import org.junit.Test;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XMLTupleIteratorTest {

    public Reader setupXMLReaderWithOneTag() throws IOException {
        File tempFile = File.createTempFile("XML", "TempFile");
        PrintWriter tempWriter = new PrintWriter(tempFile);
        tempWriter.println("<name>John</name>");
        tempWriter.close();

        return new FileReader(tempFile);
    }

    @Test
    public void testGetValueWithOneTag() throws IOException, XMLStreamException {
        Reader fileReader = setupXMLReaderWithOneTag();
        XMLTupleIterator xmlTupleIterator = new XMLTupleIterator(fileReader);

        Optional<String> possibleResult = xmlTupleIterator.getValue();
        assertTrue(possibleResult.isPresent());

        String result = possibleResult.get();
        assertEquals("John", result);
    }
}
