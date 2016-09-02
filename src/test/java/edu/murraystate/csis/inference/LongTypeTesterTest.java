package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Phillip Wright on 9/2/2016.
 */
public class LongTypeTesterTest {
    @Test
    public void validTest() {
        final String input = "1";
        final TypeTester tester = new LongTypeTester();
        final Optional<String> result = tester.test(input);
        Assert.assertTrue("The string \"1\" should return a non empty result", result.isPresent());
        Assert.assertEquals("The string \"1\" should be identified as an Long", "Long", result.get());
    }

    @Test
    public void invalidTest() {
        final String input = "x";
        final TypeTester tester = new LongTypeTester();
        final Optional<String> result = tester.test(input);
        Assert.assertFalse("The string \"x\" should return an empty result", result.isPresent());
    }
}
