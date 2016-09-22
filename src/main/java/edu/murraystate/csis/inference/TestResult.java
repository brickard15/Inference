package edu.murraystate.csis.inference;

import java.util.HashSet;
import java.util.Set;

public class TestResult {
    private final Set<String> possibleTypes;

    public TestResult(final Set<String> possibleTypes) {
        this.possibleTypes = possibleTypes;
    }

    public TestResult() {
        this(new HashSet<>());
    }

    public static TestResult merge(final TestResult result1, final TestResult result2) {
        return result1.mergeWith(result2);
    }

    public Set<String> getPossibleTypes() {
        return possibleTypes;
    }

    public TestResult mergeWith(final TestResult other) {
        final Set<String> union = new HashSet<>();
        union.addAll(possibleTypes);
        union.addAll(other.getPossibleTypes());
        return new TestResult(union);
    }
}
