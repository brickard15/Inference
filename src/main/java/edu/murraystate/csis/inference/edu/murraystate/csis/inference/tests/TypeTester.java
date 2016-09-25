package edu.murraystate.csis.inference.edu.murraystate.csis.inference.tests;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TypeTester {
    TestResult test(final String sample);

    default List<TestResult> test(final List<String> samples) {
        return samples.stream()
                .map(this::test)
                .collect(Collectors.toList());
     }

    default Stream<TestResult> test(final Stream<TestResult> input) {
        return input.map(this::test);
    }

    default TestResult test(final TestResult input) {
        return input.mergeWith(test(input.getSample()));
    }
}
