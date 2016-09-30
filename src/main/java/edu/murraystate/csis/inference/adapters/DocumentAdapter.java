package edu.murraystate.csis.inference.adapters;

import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;

import java.util.stream.Stream;

public abstract class DocumentAdapter<T> implements TestAdapter<T, Stream<TestResult>> {
    private final StreamAdapter streamAdapter;

    public DocumentAdapter(final TypeTester tester) {
        streamAdapter = new StreamAdapter(tester);
    }

    public abstract Stream<TestResult> getSampleStream(T document);

    public Stream<TestResult> test(T document) {
        final Stream<TestResult> sampleStream = getSampleStream(document);
        return streamAdapter.test(sampleStream);
    }
}
