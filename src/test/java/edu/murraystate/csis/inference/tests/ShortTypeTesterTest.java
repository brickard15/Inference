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
    
    @Test
    public void TestResultMaxInput(){
        final TestResult input = new TestResult("32767");
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult shortResult = shortTester.test(input);
        
        Assert.assertTrue(
            shortResult.getPossibleTypes().contains("Short")
        );
        
        Assert.assertFalse(
            shortResult.getPossibleTypes().isEmpty()
        );
    }
    
    @Test
    public void TestResultOverMaximumInput(){
        final TestResult input = new TestResult("32768");
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult shortResult = shortTester.test(input);
        
        Assert.assertTrue(
            shortResult.getPossibleTypes().isEmpty()
        );
    }
    
    @Test
    public void TestResultMinimumInput(){
        final TestResult input = new TestResult("-32768");
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult shortResult = shortTester.test(input);
        
        Assert.assertTrue(
            shortResult.getPossibleTypes().contains("Short")
        );
        
        Assert.assertFalse(
            shortResult.getPossibleTypes().isEmpty()
        );
    }
    
    @Test
    public void TestResultOutsideOfMinimumInput(){
        final TestResult input = new TestResult("-32769");
        final TypeTester shortTester = new ShortTypeTester();
        final TestResult shortResult = shortTester.test(input);
        
        Assert.assertFalse(
            shortResult.getPossibleTypes().contains("Short")
        );
        
        Assert.assertTrue(
            shortResult.getPossibleTypes().isEmpty()
        );
    }
}
