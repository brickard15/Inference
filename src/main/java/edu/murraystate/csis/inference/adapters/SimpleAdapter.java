package edu.murraystate.csis.inference.adapters;

import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;

public abstract class SimpleAdapter<T,U> implements TestAdapter<T,U>{
    private final TypeTester tester;

    public SimpleAdapter(final TypeTester tester){
        this.tester = tester;
    }

    public U test(final T input) {
        final String sample = transformToSample(input);
        final TestResult result = tester.test(sample);
        return transformFromTestResult(result);
    }

    public abstract String transformToSample(final T input);
    public abstract U transformFromTestResult(final TestResult result);
}
