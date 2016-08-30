package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pwright4
 */
public class StringTypeTesterTest {
    @Test
    public void testTest(){
        final String input = "test";
        final StringTypeTester tester = new StringTypeTester();
        boolean result = tester.test(input);
        Assert.assertTrue(result);
    }
}
