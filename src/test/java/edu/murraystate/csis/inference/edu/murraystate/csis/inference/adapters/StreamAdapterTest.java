package edu.murraystate.csis.inference.edu.murraystate.csis.inference.adapters;

import edu.murraystate.csis.inference.edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.edu.murraystate.csis.inference.tests.TypeTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StreamAdapterTest {
    @Test
    public void testWithStream() {
        final TypeTester adaptee = mock(TypeTester.class);

        final Set<String> possibleTypes = new HashSet<>();
        possibleTypes.add("test");
        final TestResult testResult = new TestResult(possibleTypes, "ignore");
        when(adaptee.test(anyString())).thenReturn(testResult);
        when(adaptee.test(any(TestResult.class))).thenReturn(testResult);

        final TestAdapter<Stream<TestResult>, Stream<TestResult>> t = new StreamAdapter(adaptee);

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
}
