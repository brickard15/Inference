package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;

public class TestResult {
    private final Set<String> possibleTypes;
    private final String sample;

    public TestResult(final Set<String> possibleTypes, final String sample) {
        this.possibleTypes = possibleTypes;
        this.sample = sample;
    }

    public TestResult(final String sample) {
        this(new HashSet<>(), sample);
    }

    public static TestResult merge(final TestResult result1, final TestResult result2) {
        return result1.mergeWith(result2);
    }

    public Set<String> getPossibleTypes() {
        return possibleTypes;
    }

    public String getSample() {
        return sample;
    }

    public TestResult mergeWith(final TestResult other) {
        //TODO test to make sure they have same sample
        final Set<String> union = new HashSet<>();
        union.addAll(possibleTypes);
        union.addAll(other.getPossibleTypes());
        return new TestResult(union, sample);
    }
}
