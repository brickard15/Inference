package edu.murraystate.csis.inference.summary;

import edu.murraystate.csis.inference.tests.CompositeTypeTester;
import edu.murraystate.csis.inference.tests.IntegerTypeTester;
import edu.murraystate.csis.inference.tests.StringTypeTester;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P;
import fj.P2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleSummaryTest {
    @Test
    public void noSampleTest() {
        final List<String> samples = new ArrayList<>();
        final TypeTester intTest = new IntegerTypeTester();

        final SimpleSummary summary = new SimpleSummary(intTest, samples);
        final List<P2<String, Double>> result = summary.getEstimates();
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void oneSampleTest() {
        final List<String> samples = Arrays.asList("1");
        final TypeTester intTest = new IntegerTypeTester();

        final SimpleSummary summary = new SimpleSummary(intTest, samples);
        final List<P2<String, Double>> result = summary.getEstimates();
        Assert.assertEquals(1, result.size());
        final List<P2<String, Double>> expected = Arrays.asList(P.p("Integer", 1.0));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void twoSampleTest() {
        final List<String> samples = Arrays.asList("1", "2");
        final TypeTester intTest = new IntegerTypeTester();

        final SimpleSummary summary = new SimpleSummary(intTest, samples);
        final List<P2<String, Double>> result = summary.getEstimates();
        Assert.assertEquals(1, result.size());
        final List<P2<String, Double>> expected = Arrays.asList(P.p("Integer", 1.0));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void twoSampleHalfMatchTest() {
        final List<String> samples = Arrays.asList("1", "x");
        final TypeTester intTest = new IntegerTypeTester();

        final SimpleSummary summary = new SimpleSummary(intTest, samples);
        final List<P2<String, Double>> result = summary.getEstimates();
        Assert.assertEquals(1, result.size());
        final List<P2<String, Double>> expected = Arrays.asList(P.p("Integer", 0.5));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void twoSampleTwoTypeTest() {
        final List<String> samples = Arrays.asList("1", "x");
        final TypeTester intTest = new IntegerTypeTester();
        final TypeTester stringTest = new StringTypeTester();
        final TypeTester compositeTest = new CompositeTypeTester(Arrays.asList(intTest, stringTest));

        final SimpleSummary summary = new SimpleSummary(compositeTest, samples);
        final List<P2<String, Double>> result = summary.getEstimates();
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(P.p("Integer", 0.5)));
        Assert.assertTrue(result.contains(P.p("String", 1.0)));
    }
}
