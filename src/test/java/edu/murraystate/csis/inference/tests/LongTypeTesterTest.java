package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class LongTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester tester = new LongTypeTester();
        final TestResult result = tester.test(input);
        Assert.assertFalse("The string \"1\" should return a non empty result",
                result.getPossibleTypes().isEmpty());
        Assert.assertTrue("The string \"1\" should be identified as an Long",
                result.getPossibleTypes().contains("Long"));
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester tester = new LongTypeTester();
        final TestResult result = tester.test(input);
        Assert.assertTrue("The string \"x\" should return an empty result",
                result.getPossibleTypes().isEmpty());
    }
}
