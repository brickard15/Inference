package edu.murraystate.csis.inference.adapters.documents;

import edu.murraystate.csis.inference.adapters.TestAdapter;
import edu.murraystate.csis.inference.adapters.aggregation.StreamWithPathAdapter;
import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P2;

import java.util.stream.Stream;

public abstract class DocumentAdapter<T> implements TestAdapter<T, Stream<P2<String, TestResult>>> {
    private final StreamWithPathAdapter streamWithPathAdapter;

    public DocumentAdapter(final TypeTester tester) {
        streamWithPathAdapter = new StreamWithPathAdapter(tester);
    }

    public abstract Stream<P2<String, TestResult>> getSampleStream(T document);

    public Stream<P2<String, TestResult>> test(T document) {
        final Stream<P2<String, TestResult>> sampleStream = getSampleStream(document);
        return streamWithPathAdapter.test(sampleStream);
    }
}
