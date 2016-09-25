package edu.murraystate.csis.inference.edu.murraystate.csis.inference.tests;

import java.util.List;

public class CompositeTypeTester implements TypeTester{
    private final List<TypeTester> testers;

    public CompositeTypeTester(final List<TypeTester> testers){
        this.testers = testers;
    }

    @Override
    public TestResult test(final String sample) {
        return testers.stream()
                .map(tester -> tester.test(sample))
                .reduce(new TestResult(sample), TestResult::merge);
    }
}
