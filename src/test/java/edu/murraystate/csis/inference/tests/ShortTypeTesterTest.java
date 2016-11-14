package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class ShortTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult result = shortTester.test(input);
        
        Assert.assertFalse(
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            result.getPossibleTypes().contains("Short")
        );
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult result = shortTester.test(input);
        
        Assert.assertTrue(
            result.getPossibleTypes().isEmpty()
        );
    }
}
