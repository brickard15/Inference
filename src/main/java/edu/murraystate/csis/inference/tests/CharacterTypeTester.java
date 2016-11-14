package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;

public final class CharacterTypeTester implements TypeTester {

    private static final String typeString = "Character";

    @Override
    public TestResult test(final String sample) {
        if (sample.length() == 1) {
            final Set<String> result = new HashSet<>();
            result.add(typeString);
            return new TestResult(result, sample);
        }else{
            return new TestResult(sample);
        }
    }
}
