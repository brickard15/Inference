package edu.murraystate.csis.inference.adapters;

import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleAdapterTest {
    @Test
    public void adaptingTest(){
        final TypeTester mockedTester = mock(TypeTester.class);

        final Set<String> possibleTypes = new HashSet<>();
        possibleTypes.add("Integer");
        final TestResult expected = new TestResult(possibleTypes,"1");

        when(mockedTester.test("1")).thenReturn(expected);

        final SimpleAdapter<Integer,String> adapter = new SimpleAdapter<Integer, String>(mockedTester) {
            @Override
            public String transformToSample(Integer input) {
                return input.toString();
            }

            @Override
            public String transformFromTestResult(TestResult result) {
                return result.getSample()+result.getPossibleTypes().toArray()[0];
            }
        };

        final String result = adapter.test(1);
        Assert.assertEquals("1Integer",result);
    }
}
