package edu.murraystate.csis.inference;

import java.util.List;
import java.util.stream.Collectors;

public interface TypeTester {
    TestResult test(final String sample);

    default List<TestResult> test(final List<String> samples) {
        return samples.stream()
                .map(sample -> test(sample))
                .collect(Collectors.toList());
     }
}
