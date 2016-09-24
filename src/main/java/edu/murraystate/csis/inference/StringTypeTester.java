package edu.murraystate.csis.inference;

import java.util.HashSet;
import java.util.Set;

public class StringTypeTester implements TypeTester {

    public StringTypeTester() {

    }

    @Override
    public TestResult test(final String sample) {
        final Set<String> result = new HashSet<>();
        result.add("String");
        return new TestResult(result, sample);
    }

}
