package edu.murraystate.csis.inference.adapters.documents.xml;

import org.junit.Test;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XMLTupleIteratorTest {

    public Reader setupXMLReaderWithOneTag() throws IOException {
        File tempFile = File.createTempFile("XML", "ReaderWithOneTag");
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

    public Reader setupXMLReaderWithNestedTags() throws IOException {
        File tempFile = File.createTempFile("XML", "ReaderWithNestedTags");
        PrintWriter tempWriter = new PrintWriter(tempFile);
        tempWriter.println("<name><first>John</first><last>Doe</last></name>");
        tempWriter.close();

        return new FileReader(tempFile);
    }

    @Test
    public void testGetValueWithNestedTags() throws IOException, XMLStreamException {
        Reader xmlReader = setupXMLReaderWithNestedTags();
        XMLTupleIterator xmlTupleIterator = new XMLTupleIterator(xmlReader);

        Optional<String> possibleResult = xmlTupleIterator.getValue();
        assertTrue(possibleResult.isPresent());
        String result = possibleResult.get();
        assertEquals("John", result);

        Optional<String> possibleResult2 = xmlTupleIterator.getValue();
        assertTrue(possibleResult2.isPresent());
        String result2 = possibleResult2.get();
        assertEquals("Doe", result2);
    }

    public Reader setupXMLReaderWithNoContent() throws IOException {
        File tempFile = File.createTempFile("XML", "ReaderWithNoContent");
        PrintWriter tempWriter = new PrintWriter(tempFile);
        tempWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><name><first></first><last></last></name>");
        tempWriter.close();

        return new FileReader(tempFile);
    }

    @Test
    public void testGetValueWithNoContent() throws IOException, XMLStreamException {
        Reader xmlReader = setupXMLReaderWithNoContent();
        XMLTupleIterator xmlTupleIterator = new XMLTupleIterator(xmlReader);

        Optional<String> possibleResult = xmlTupleIterator.getValue();
        assertFalse(possibleResult.isPresent());
    }
}
