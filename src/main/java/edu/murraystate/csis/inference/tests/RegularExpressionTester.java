package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTester implements TypeTester {
    private final String typeString;
    private final Pattern pattern;

    public RegularExpressionTester(final String typeString, final String regex) {
        this.typeString = typeString;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public TestResult test(final String sample) {
        final Set<String> result = new HashSet<>();
        final Matcher m = pattern.matcher(sample);
        if (m.matches()) {
            result.add(typeString);
        }
        return new TestResult(result, sample);
    }
}
