package edu.murraystate.csis.inference.adapters.documents.xml;

import fj.P;
import fj.P2;
import java.io.Reader;
import java.util.Iterator;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

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
        Optional<P2<String, String>> result = Optional.empty();

        Optional<String> possiblePath = getPath();
        Optional<String> possibleValue = getValue();
        if (possiblePath.isPresent() && possibleValue.isPresent()){
            P2<String, String> tuple = P.p(possiblePath.get(),possibleValue.get());
            result = Optional.of(tuple);
        }

        return result;
    }

    public Optional<String> getPath(){

        return Optional.empty();
    }

    public Optional<String> getValue(){
        try {
            if (xmlEventReader.hasNext()) {
                XMLEvent nextEvent = xmlEventReader.nextEvent();
                if (nextEvent.isCharacters()) {
                    return Optional.of(nextEvent.toString());
                } else {
                    return getValue();
                }
            }
        } catch (XMLStreamException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
