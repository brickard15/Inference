package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author pwright4
 */
public class StringTypeTesterTest {
    @Test
    public void testTest() {
        final String input = "test";
        final TypeTester tester = new StringTypeTester();
        final Optional<String> result = tester.test(input);
        Assert.assertTrue("The string \"test\" should return a non empty result", result.isPresent());
        Assert.assertEquals("The string \"test\" should be identified as a String", "String", result.get());
    }
}
