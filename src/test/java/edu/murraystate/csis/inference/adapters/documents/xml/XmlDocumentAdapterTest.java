package edu.murraystate.csis.inference.adapters.documents.xml;

import edu.murraystate.csis.inference.adapters.documents.DocumentAdapter;
import edu.murraystate.csis.inference.tests.TypeTester;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class XmlDocumentAdapterTest {
    @Test
    public void getSampleStreamReturnsAStream(){
        DocumentAdapter testDocumentAdapter = new XmlDocumentAdapter(mock(TypeTester.class));
        
        Object mockObject = mock(Object.class);
        
        assertThat(testDocumentAdapter.getSampleStream(mockObject), instanceOf(Stream.class));
    }
}
