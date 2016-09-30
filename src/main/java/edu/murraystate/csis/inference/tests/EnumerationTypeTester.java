package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumerationTypeTester implements TypeTester {
    final int limit;
    final String typeTemplate = "Enumeration(%s)";
    final Set<String> valuesSeen;

    public EnumerationTypeTester(final int limit) {
        this.limit = limit;
        this.valuesSeen = new HashSet<>();
    }

    @Override
    public TestResult test(String sample) {
        valuesSeen.add(sample);
        return getResult(sample);
    }

    protected TestResult getResult(final String sample) {
        if (valuesSeen.size() > limit) {
            return new TestResult(sample);
        } else {
            return getEnumResult(sample);
        }
    }

    protected TestResult getEnumResult(final String sample) {
        final Set<String> possibleTypes = new HashSet<>();
        possibleTypes.add(getTypeString());
        return new TestResult(possibleTypes, sample);
    }

    protected String getTypeString() {
        return String.format(typeTemplate, getEnumValuesString());
    }

    protected String getEnumValuesString() {
        return String.join("|", valuesSeen.stream().sorted().collect(Collectors.toList()));
    }
}
