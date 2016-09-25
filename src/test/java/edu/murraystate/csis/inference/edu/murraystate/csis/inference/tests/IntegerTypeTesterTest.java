package edu.murraystate.csis.inference.edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;


public class IntegerTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester tester = new IntegerTypeTester();
        final TestResult result = tester.test(input);
        Assert.assertFalse("The string \"1\" should return a non empty result",
                result.getPossibleTypes().isEmpty());
        Assert.assertTrue("The string \"1\" should be identified as an Integer",
                result.getPossibleTypes().contains("Integer"));
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester tester = new IntegerTypeTester();
        final TestResult result = tester.test(input);
        Assert.assertTrue("The string \"x\" should return an empty result",
                result.getPossibleTypes().isEmpty());
    }
}
