package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class RegularExpressionTesterTest {
    @Test
    public void simpleValidTest() {
        final String regex = "xyz";
        final String type = "TestRegExType";
        final String sample = regex;
        doTest(regex, type, sample, true);
    }

    @Test
    public void simpleInvalidTest() {
        final String regex = "abc";
        final String type = "TestReExType";
        final String sample = "xyz";
        doTest(regex, type, sample, false);
    }

    @Test
    public void partialMatchTest() {
        // We only want a valid result when the whole sample matches
        final String regex = "abc";
        final String type = "TestRegExType";
        final String sample = "abcd";
        doTest(regex, type, sample, false);
    }

    @Test
    public void moreComplexTest() {
        final String regex = "b[aoe]t";
        final String type = "TestRegExType";
        doTest(regex, type, "bet", true);
        doTest(regex, type, "bot", true);
        doTest(regex, type, "bat", true);
        doTest(regex, type, "bats", false);
        doTest(regex, type, "bats", false);
    }

    private void doTest(final String regex, final String typeString, final String sample, final boolean shouldPass) {
        final TypeTester tester = new RegularExpressionTester(typeString, regex);
        final TestResult result = tester.test(sample);
        if (shouldPass) {
            positiveTest(regex, typeString, sample, result);
        } else {
            negativeTest(regex, typeString, result);
        }
    }

    private void positiveTest(final String regex, final String typeString, final String sample, final TestResult result) {
        final String nonEmptyMessage = String.format("Input %s with pattern %s should return a non empty result", sample, regex);
        Assert.assertFalse(nonEmptyMessage, result.getPossibleTypes().isEmpty());
        final String typeMessage = String.format("Input %s should have type %s", sample, typeString);
        Assert.assertTrue(typeMessage, result.getPossibleTypes().contains(typeString));
    }

    private void negativeTest(final String regex, final String sample, final TestResult result) {
        final String emptyMessage = String.format("Input %s should return an empty result with pattern %s", sample, regex);
        Assert.assertTrue(emptyMessage, result.getPossibleTypes().isEmpty());
    }
}
