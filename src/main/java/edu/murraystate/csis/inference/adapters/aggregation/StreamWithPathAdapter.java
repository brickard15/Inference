package edu.murraystate.csis.inference.adapters.aggregation;

import edu.murraystate.csis.inference.adapters.TestAdapter;
import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P2;

import java.util.stream.Stream;

public class StreamWithPathAdapter implements TestAdapter<Stream<P2<String, TestResult>>, Stream<P2<String, TestResult>>> {
    private final TypeTester tester;

    public StreamWithPathAdapter(final TypeTester tester) {
        this.tester = tester;
    }

    @Override
    public Stream<P2<String, TestResult>> test(final Stream<P2<String, TestResult>> input) {
        return input.map(tuple -> tuple.map2(tester::test));
    }
}
