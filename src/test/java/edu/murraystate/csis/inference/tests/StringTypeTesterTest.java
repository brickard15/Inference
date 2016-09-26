package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class StringTypeTesterTest {
    @Test
    public void testTest() {
        final String input = "test";
        final TypeTester tester = new StringTypeTester();
        final TestResult result = tester.test(input);
        Assert.assertFalse("The string \"test\" should return a non empty result",
                result.getPossibleTypes().isEmpty());
        Assert.assertTrue("The string \"test\" should be identified as a String",
                result.getPossibleTypes().contains("String"));
    }
}
