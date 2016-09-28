package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TypeTesterTest {
    @Test
    public void testResultAsInput() {
        final String inputSample = "testSample";
        final TestResult input = new TestResult(inputSample);
        final TypeTester tester = new DummyTest();
        final TestResult result = tester.test(input);

        Assert.assertEquals(1, result.getPossibleTypes().size());
        Assert.assertTrue(result.getPossibleTypes().contains("test"));
        Assert.assertEquals(inputSample, result.getSample());
    }

    public class DummyTest implements TypeTester{

        @Override
        public TestResult test(String sample) {
            final Set<String> possibleTypes = new HashSet<>();
            possibleTypes.add("test");
            return new TestResult(possibleTypes, sample);
        }
    }
}
