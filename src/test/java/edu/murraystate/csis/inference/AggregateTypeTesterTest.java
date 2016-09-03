package edu.murraystate.csis.inference;

import fj.P;
import fj.P2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Phillip Wright on 9/3/2016.
 */
public class AggregateTypeTesterTest {
    @Test
    public void oneSampleNoResultsNoTesters() {
        final List<TypeTester> testers = getTesters();
        final List<String> samples = getSampleList("testsample");
        final List<P2<String, Double>> expected = getExpected();
        doTesting(expected, testers, samples);
    }

    @Test
    public void oneSampleNoResultsOneTester() {
        final List<TypeTester> testers = getTesters(new IntegerTypeTester());
        final List<String> samples = getSampleList("testsample");
        final List<P2<String, Double>> expected = getExpected();
        doTesting(expected, testers, samples);
    }

    @Test
    public void oneSampleNoResultsTwoTesters() {
        final List<TypeTester> testers = getTesters(new IntegerTypeTester(), new LongTypeTester());
        final List<String> samples = getSampleList("testsample");
        final List<P2<String, Double>> expected = getExpected();
        doTesting(expected, testers, samples);
    }

    @Test
    public void twoSamplesNoResultsOneTester() {
        final List<TypeTester> testers = getTesters(new IntegerTypeTester());
        final List<String> samples = getSampleList("testsample", "testsample2");
        final List<P2<String, Double>> expected = getExpected();
        doTesting(expected, testers, samples);
    }

    @Test
    public void twoSamplesNoResultsTwoTesters() {
        final List<TypeTester> testers = getTesters(new IntegerTypeTester(), new LongTypeTester());
        final List<String> samples = getSampleList("testsample", "testsample2");
        final List<P2<String, Double>> expected = getExpected();
        doTesting(expected, testers, samples);
    }

    @Test
    public void oneSampleOneResultOneTester() {
        final List<TypeTester> testers = getTesters(new StringTypeTester());
        final List<String> samples = getSampleList("testsample");
        final List<P2<String, Double>> expected = getExpected(P.p("String", 1.0));
        doTesting(expected, testers, samples);
    }

    @Test
    public void twoSamplesOneResultOneTester() {
        final List<TypeTester> testers = getTesters(new StringTypeTester());
        final List<String> samples = getSampleList("testsample", "testsample2");
        final List<P2<String, Double>> expected = getExpected(P.p("String", 1.0));
        doTesting(expected, testers, samples);
    }

    @Test
    public void oneSampleOneResultTwoTesters() {
        final List<TypeTester> testers = getTesters(new StringTypeTester(), new IntegerTypeTester());
        final List<String> samples = getSampleList("testsample");
        final List<P2<String, Double>> expected = getExpected(P.p("String", 1.0));
        doTesting(expected, testers, samples);
    }

    @Test
    public void oneSampleTwoResultsTwoTesters() {
        final List<TypeTester> testers = getTesters(new StringTypeTester(), new IntegerTypeTester());
        final List<String> samples = getSampleList("100");
        final List<P2<String, Double>> expected = getExpected(P.p("Integer", 1.0), P.p("String", 1.0));
        doTesting(expected, testers, samples);
    }

    @Test
    public void twoSamplesTwoResultsTwoTestersAllAndHalf() {
        final List<TypeTester> testers = getTesters(new StringTypeTester(), new IntegerTypeTester());
        final List<String> samples = getSampleList("100", "sample");
        final List<P2<String, Double>> expected = getExpected(P.p("String", 1.0), P.p("Integer", 0.5));
        doTesting(expected, testers, samples);
    }

    private void doTesting(
            final List<P2<String, Double>> expected,
            final List<TypeTester> tests,
            final List<String> samples) {
        final List<P2<String, Double>> result = getResults(tests, samples);
        Assert.assertEquals(expected.size(), result.size());
        // TODO Need to rewrite so that order doesn't matter for same percentage?
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i)._1(), result.get(i)._1());
            Assert.assertEquals(expected.get(i)._2(), result.get(i)._2(), 0.001);
        }
    }

    private List<P2<String, Double>> getResults(final List<TypeTester> tests, final List<String> samples) {
        final AggregateTypeTester multiTester = new AggregateTypeTester(tests);
        return multiTester.test(samples);
    }

    private List<P2<String, Double>> getExpected(final P2<String, Double>... expected) {
        final List<P2<String, Double>> result = new ArrayList<>();
        Collections.addAll(result, expected);
        return result;
    }

    private List<TypeTester> getTesters(final TypeTester... testers) {
        final List<TypeTester> result = new ArrayList<>();
        Collections.addAll(result, testers);
        return result;
    }

    private List<String> getSampleList(final String... samples) {
        final List<String> result = new ArrayList<>();
        Collections.addAll(result, samples);
        return result;
    }
}
