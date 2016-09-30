package edu.murraystate.csis.inference.tests;

import edu.murraystate.csis.inference.adapters.StreamAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumerationTypeTesterTest {
    @Test
    public void enumStringTestEmpty() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        final String result = tester.getEnumValuesString();
        final String expected = "";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void enumStringTestOne() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        final String result = tester.getEnumValuesString();
        final String expected = "ORANGE";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void enumStringTestTwo() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        tester.test("RED");
        final String result = tester.getEnumValuesString();
        final String expected = "ORANGE|RED";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void typeStringTestEmpty() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        final String result = tester.getTypeString();
        final String expected = "Enumeration()";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void typeStringTestOne() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        final String result = tester.getTypeString();
        final String expected = "Enumeration(ORANGE)";
        Assert.assertEquals(expected, result);
    }


    @Test
    public void typeStringTestTwo() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        tester.test("RED");
        final String result = tester.getTypeString();
        final String expected = "Enumeration(ORANGE|RED)";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void resultTestOne() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        final TestResult result = tester.getResult("ORANGE");
        Assert.assertEquals("ORANGE", result.getSample());
        Assert.assertTrue(result.getPossibleTypes().contains("Enumeration(ORANGE)"));
        Assert.assertEquals(1, result.getPossibleTypes().size());
    }

    @Test
    public void resultTestTwo() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        tester.test("RED");
        final TestResult result = tester.getResult("RED");
        Assert.assertEquals("RED", result.getSample());
        Assert.assertTrue(result.getPossibleTypes().contains("Enumeration(ORANGE|RED)"));
        Assert.assertEquals(1, result.getPossibleTypes().size());
    }

    @Test
    public void testTestOneWithinLimit() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        final TestResult result = tester.test("ORANGE");
        Assert.assertEquals("ORANGE", result.getSample());
        Assert.assertTrue(result.getPossibleTypes().contains("Enumeration(ORANGE)"));
        Assert.assertEquals(1, result.getPossibleTypes().size());
    }

    @Test
    public void testTestTwoWithinLimit() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(10);
        tester.test("ORANGE");
        final TestResult result = tester.test("RED");
        Assert.assertEquals("RED", result.getSample());
        Assert.assertTrue(result.getPossibleTypes().contains("Enumeration(ORANGE|RED)"));
        Assert.assertEquals(1, result.getPossibleTypes().size());
    }

    @Test
    public void testOneInLimitOneOut() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(1);
        final TestResult one = tester.test("ORANGE");

        Assert.assertTrue(one.getPossibleTypes().contains("Enumeration(ORANGE)"));
        Assert.assertEquals(1, one.getPossibleTypes().size());
        Assert.assertEquals("ORANGE", one.getSample());

        final TestResult two = tester.test("RED");

        Assert.assertEquals("RED", two.getSample());
        Assert.assertEquals(0, two.getPossibleTypes().size());
    }

    @Test
    public void testTwoInLimitRepeatingOne() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(2);
        final TestResult one = tester.test("ORANGE");

        Assert.assertTrue(one.getPossibleTypes().contains("Enumeration(ORANGE)"));
        Assert.assertEquals(1, one.getPossibleTypes().size());
        Assert.assertEquals("ORANGE", one.getSample());

        final TestResult two = tester.test("RED");

        Assert.assertEquals("RED", two.getSample());
        Assert.assertEquals(1, two.getPossibleTypes().size());
        Assert.assertTrue(two.getPossibleTypes().contains("Enumeration(ORANGE|RED)"));

        final TestResult three = tester.test("ORANGE");

        Assert.assertEquals("ORANGE", three.getSample());
        Assert.assertEquals(1, three.getPossibleTypes().size());
        Assert.assertTrue(three.getPossibleTypes().contains("Enumeration(ORANGE|RED)"));
    }

    @Test
    public void streamTest() {
        final EnumerationTypeTester tester = new EnumerationTypeTester(3);
        final StreamAdapter adapter = new StreamAdapter(tester);

        final List<String> inputs = Arrays.asList("RED", "ORANGE", "GREEN", "RED", "BLUE", "RED");

        final Stream<TestResult> resultStream = adapter.test(inputs.stream().map(TestResult::new));

        final List<TestResult> testResults = resultStream.collect(Collectors.toList());

        testResult(testResults.get(0), 1, "RED", "Enumeration(RED)");
        testResult(testResults.get(1), 1, "ORANGE", "Enumeration(ORANGE|RED)");
        testResult(testResults.get(2), 1, "GREEN", "Enumeration(GREEN|ORANGE|RED)");
        testResult(testResults.get(3), 1, "RED", "Enumeration(GREEN|ORANGE|RED)");
        testResult(testResults.get(4), 0, "BLUE", "");
        testResult(testResults.get(5), 0, "RED", "");

    }

    private void testResult(final TestResult result, final int size, final String expectedSample, final String expectedType) {
        Assert.assertEquals(expectedSample, result.getSample());
        Assert.assertEquals(size, result.getPossibleTypes().size());
        if (size > 0) {
            Assert.assertTrue(result.getPossibleTypes().contains(expectedType));
        }
    }

}
