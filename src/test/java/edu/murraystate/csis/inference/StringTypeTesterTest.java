package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author pwright4
 */
public class StringTypeTesterTest {
    @Test
    public void testTest() {
        final String input = "test";
        final TypeTester tester = new StringTypeTester();
        final List<String> result = tester.test(input);
        Assert.assertFalse("The string \"test\" should return a non empty result", result.isEmpty());
        Assert.assertEquals("The string \"test\" should be identified as a String", "String", result.get(0));
    }
}
