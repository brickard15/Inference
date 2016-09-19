package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author pwright4
 */
public class IntegerTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester tester = new IntegerTypeTester();
        final List<String> result = tester.test(input);
        Assert.assertFalse("The string \"1\" should return a non empty result", result.isEmpty());
        Assert.assertEquals("The string \"1\" should be identified as an Integer", "Integer", result.get(0));
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester tester = new IntegerTypeTester();
        final List<String> result = tester.test(input);
        Assert.assertTrue("The string \"x\" should return an empty result", result.isEmpty());
    }
}
