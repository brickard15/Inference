package edu.murraystate.csis.inference.adapters.documents.xml;

import edu.murraystate.csis.inference.adapters.documents.DocumentAdapter;
import edu.murraystate.csis.inference.tests.TypeTester;
import java.util.stream.Stream;

public class XmlDocumentAdapter extends DocumentAdapter{

    public XmlDocumentAdapter(TypeTester tester) {
        super(tester);
    }

    @Override
    public Stream getSampleStream(Object document) {
        return null;
    }
    
}
