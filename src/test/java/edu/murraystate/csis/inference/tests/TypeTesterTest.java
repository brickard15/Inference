package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeTesterTest {
    @Test
    public void aggregateTest(){
        final TypeTester t = new DummyTest();
        final List<String> samples = new ArrayList<>();
        samples.add("input1");
        samples.add("input2");
        samples.add("input3");

        final List<TestResult> results = t.test(samples);
        Assert.assertEquals(3, results.size());
        for (final TestResult tr : results) {
            Assert.assertEquals(1, tr.getPossibleTypes().size());
            Assert.assertTrue(tr.getPossibleTypes().contains("test"));
        }
    }

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

    @Test
    public void testWithStream() {
        final TypeTester t = new DummyTest();
        final List<String> samples = new ArrayList<>();
        samples.add("input1");
        samples.add("input2");
        samples.add("input3");

        final Stream<TestResult> inputStream = samples.stream().map(TestResult::new);
        final List<TestResult> results = t.test(inputStream).collect(Collectors.toList());

        Assert.assertEquals(3, results.size());
        for (final TestResult tr : results) {
            Assert.assertEquals(1, tr.getPossibleTypes().size());
            Assert.assertTrue(tr.getPossibleTypes().contains("test"));
        }
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
