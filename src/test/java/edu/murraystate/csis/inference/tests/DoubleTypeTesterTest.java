package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class DoubleTypeTesterTest {
    
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester doubleTester = new DoubleTypeTester();
        final TestResult result = doubleTester.test(input);
        
        Assert.assertFalse(
            "The string \"1\" should return a non empty result",
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            "The string \"1\" should be identified as an Double",
            result.getPossibleTypes().contains("Double")
        );
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester doubleTester = new DoubleTypeTester();
        final TestResult result = doubleTester.test(input);
        Assert.assertTrue(
            "The string \"x\" should return an empty result",
            result.getPossibleTypes().isEmpty()
        );
    }
    
    @Test
    public void testResultInputValid() {
        final TestResult input = new TestResult("2.0");
        final TypeTester doubleTester = new DoubleTypeTester();
        final TestResult doubleResult = doubleTester.test(input);
        
        Assert.assertTrue(
            "The string \"2.0\" should be identified as an Double",
            doubleResult.getPossibleTypes().contains("Double")
        );
        
        Assert.assertFalse(
            "The string \"2.0\" should return a non empty result",
            doubleResult.getPossibleTypes().isEmpty()
        );
    }
}
