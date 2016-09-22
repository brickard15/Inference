package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public class DummyTest implements TypeTester{

        @Override
        public TestResult test(String sample) {
            final Set<String> possibleTypes = new HashSet<>();
            possibleTypes.add("test");
            return new TestResult(possibleTypes);
        }
    }
}
