package edu.murraystate.csis.inference.adapters;

import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListAdapterTest {
    @Test
    public void aggregateTest() {
        final TypeTester adaptee = mock(TypeTester.class);

        final Set<String> possibleTypes = new HashSet<>();
        possibleTypes.add("test");
        final TestResult testResult = new TestResult(possibleTypes, "ignore");

        when(adaptee.test(anyString())).thenReturn(testResult);
        // TODO actually capture the string and use it to construct a more accurate test result

        final TestAdapter<List<String>, List<TestResult>> t = new ListAdapter(adaptee);

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
        // TODO use mockito to make sure the right methods were called as well.
    }
}
