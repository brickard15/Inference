package edu.murraystate.csis.inference.adapters.documents.xml;

import fj.P2;
import java.io.Reader;
import java.util.Iterator;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class XMLTupleIterator implements Iterator<Optional<P2<String, String>>>{

    final private XMLEventReader xmlEventReader;
    final private Reader reader;
    
    public XMLTupleIterator(Reader reader) throws XMLStreamException{
        try {
            this.reader = reader;
            xmlEventReader = XMLInputFactory.newInstance().createXMLEventReader(reader);
        } catch (XMLStreamException ex) {
            throw new XMLStreamException("Invalid Reader");
        }
    }
    
    @Override
    public boolean hasNext() {
        return xmlEventReader.hasNext();
    }

    @Override
    public Optional<P2<String, String>> next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
