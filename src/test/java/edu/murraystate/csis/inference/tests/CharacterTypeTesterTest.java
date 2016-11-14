package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

public class CharacterTypeTesterTest{
    
    @Test
    public void validTest() {
        final String input = "C";
        final TypeTester characterTypeTester = new CharacterTypeTester();
        final TestResult characterResult = characterTypeTester.test(input);
        
        Assert.assertFalse(
            characterResult.getPossibleTypes().isEmpty()
        );
        
        Assert.assertTrue(
            characterResult.getPossibleTypes().contains("Character")
        );
    }
    
    @Test
    public void invalidTest() {
        final String input = "123";
        final TypeTester characterTypeTester = new CharacterTypeTester();
        final TestResult characterResult = characterTypeTester.test(input);
        
        Assert.assertTrue(
                characterResult.getPossibleTypes().isEmpty()
        );
    }
}
