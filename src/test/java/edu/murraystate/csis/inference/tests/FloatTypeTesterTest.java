package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class FloatTypeTesterTest {
    
    @Test
    public void validTest() {
        final String input = "1.0";
        final TypeTester floatTester = new FloatTypeTester();
        final TestResult result = floatTester.test(input);
        
        Assert.assertFalse(
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            result.getPossibleTypes().contains("Float")
        );
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester floatTester = new FloatTypeTester();
        final TestResult result = floatTester.test(input);
        
        Assert.assertTrue(
            result.getPossibleTypes().isEmpty()
        );
    }
}
