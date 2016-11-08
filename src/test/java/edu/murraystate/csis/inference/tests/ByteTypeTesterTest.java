package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class ByteTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester byteTester = new ByteTypeTester();
        final TestResult result = byteTester.test(input);
        
        Assert.assertFalse(
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            result.getPossibleTypes().contains("Byte")
        );
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester byteTester = new ByteTypeTester();
        final TestResult result = byteTester.test(input);
        
        Assert.assertTrue(
            result.getPossibleTypes().isEmpty()
        );
    }
}
