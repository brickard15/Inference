package edu.murraystate.csis.inference.tests;

public interface TypeTester {
    TestResult test(final String sample);

    default TestResult test(final TestResult input) {
        return input.mergeWith(test(input.getSample()));
    }
}
