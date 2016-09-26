package edu.murraystate.csis.inference.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestResultTest {
    @Test
    public void creationTestWithTypes() {
        final Set<String> types = new HashSet<>();
        types.add("one");
        types.add("two");

        final String testSample = "testSample";

        final TestResult r = new TestResult(types, testSample);
        Assert.assertEquals(types, r.getPossibleTypes());
        Assert.assertEquals(testSample, r.getSample());
    }

    @Test
    public void creationTestWithoutTypes() {
        final String testSample = "testSample";
        final TestResult r = new TestResult(testSample);
        Assert.assertNotNull(r.getPossibleTypes());
        Assert.assertTrue(r.getPossibleTypes().isEmpty());
        Assert.assertEquals(testSample, r.getSample());
    }

    @Test
    public void mergeWithTest() {
        final Set<String> leftTypes = new HashSet<>();
        leftTypes.add("one");

        final Set<String> rightTypes = new HashSet<>();
        rightTypes.add("two");

        final String testSample = "testSample";

        final TestResult left = new TestResult(leftTypes, testSample);
        final TestResult right = new TestResult(rightTypes, testSample);

        final TestResult merged = left.mergeWith(right);

        Assert.assertNotEquals(leftTypes, merged.getPossibleTypes());
        Assert.assertNotEquals(rightTypes, merged.getPossibleTypes());
        Assert.assertEquals(2, merged.getPossibleTypes().size());
        Assert.assertTrue(merged.getPossibleTypes().contains("one"));
        Assert.assertTrue(merged.getPossibleTypes().contains("two"));
        Assert.assertEquals(testSample, merged.getSample());
    }

    @Test
    public void mergeTest() {
        final Set<String> leftTypes = new HashSet<>();
        leftTypes.add("one");

        final Set<String> rightTypes = new HashSet<>();
        rightTypes.add("two");

        final String testSample = "testSample";


        final TestResult left = new TestResult(leftTypes, testSample);
        final TestResult right = new TestResult(rightTypes, testSample);

        final TestResult merged = TestResult.merge(left, right);

        Assert.assertNotEquals(leftTypes, merged.getPossibleTypes());
        Assert.assertNotEquals(rightTypes, merged.getPossibleTypes());
        Assert.assertEquals(2, merged.getPossibleTypes().size());
        Assert.assertTrue(merged.getPossibleTypes().contains("one"));
        Assert.assertTrue(merged.getPossibleTypes().contains("two"));
        Assert.assertEquals(testSample, merged.getSample());
    }
}
