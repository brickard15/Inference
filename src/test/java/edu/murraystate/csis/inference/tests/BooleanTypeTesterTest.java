package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class BooleanTypeTesterTest {
    @Test
    public void validTrueTest() {
        final String input = "true";
        final TypeTester booleanTester = new BooleanTypeTester();
        final TestResult result = booleanTester.test(input);
        
        Assert.assertFalse(
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            result.getPossibleTypes().contains("Boolean")
        );
    }
    
    @Test
    public void validFalseTest() {
        final String input = "false";
        final TypeTester booleanTester = new BooleanTypeTester();
        final TestResult result = booleanTester.test(input);
        
        Assert.assertFalse(
            result.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            result.getPossibleTypes().contains("Boolean")
        );
    }
    
    @Test
    public void invalidTest() {
        final String input = "False";
        final TypeTester booleanTester = new BooleanTypeTester();
        final TestResult result = booleanTester.test(input);
        
        Assert.assertTrue(
                result.getPossibleTypes().isEmpty()
        );
    }
    
    @Test
    public void nullInputTest(){
        final String input = null;
        final TypeTester booleanTester = new BooleanTypeTester();
        final TestResult result = booleanTester.test(input);
        
        Assert.assertTrue(
                result.getPossibleTypes().isEmpty()
        );
    }
}
