package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class TryCatchTester<T> implements TypeTester {
    private final Function<String, T> testFunction;
    private final String typeString;

    public TryCatchTester(final String typeString, Function<String, T> testFunction) {
        this.typeString = typeString;
        this.testFunction = testFunction;
    }

    @Override
    public TestResult test(final String sample) {
        try {
            testFunction.apply(sample);
            final Set<String> result = new HashSet<>();
            result.add(typeString);
            return new TestResult(result, sample);
        } catch (Exception e) {
            return new TestResult(sample);
        }
    }
}
