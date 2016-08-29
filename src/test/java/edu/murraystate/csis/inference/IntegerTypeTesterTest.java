/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pwright4
 */
public class IntegerTypeTesterTest {
    @Test
    public void validTest(){
        final String input = "1";
        final IntegerTypeTester tester = new IntegerTypeTester();
        boolean result = tester.test(input);
        Assert.assertTrue(result);
    }
    
    @Test
    public void invalidTest(){
        final String input = "x";
        final IntegerTypeTester tester = new IntegerTypeTester();
        boolean result = tester.test(input);
        Assert.assertFalse(result);
    }
}
