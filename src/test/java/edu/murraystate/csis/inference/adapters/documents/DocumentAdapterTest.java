package edu.murraystate.csis.inference.adapters.documents;

import edu.murraystate.csis.inference.tests.*;
import fj.P;
import fj.P2;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DocumentAdapterTest {
    @Test
    public void simpleTest() {
        final List<String> inputs = Arrays.asList("Phillip", "33", "YES");

        final TypeTester intTest = new IntegerTypeTester();
        final TypeTester stringTest = new StringTypeTester();
        final List<TypeTester> testers = Arrays.asList(intTest, stringTest);

        final TypeTester composite = new CompositeTypeTester(testers);

        final DocumentAdapter<List<String>> adapter = new DocumentAdapter<List<String>>(composite) {
            @Override
            public Stream<P2<String, TestResult>> getSampleStream(List<String> document) {
                return document.stream()
                        .map(sample -> P.p(sample, new TestResult(sample)));
            }
        };

        final List<P2<String, TestResult>> results = adapter.test(inputs).collect(Collectors.toList());

        Assert.assertEquals("Phillip", results.get(0)._2().getSample());
        Assert.assertEquals(1, results.get(0)._2().getPossibleTypes().size());
        Assert.assertTrue(results.get(0)._2().getPossibleTypes().contains("String"));

        Assert.assertEquals("33", results.get(1)._2().getSample());
        Assert.assertEquals(2, results.get(1)._2().getPossibleTypes().size());
        Assert.assertTrue(results.get(1)._2().getPossibleTypes().contains("String"));
        Assert.assertTrue(results.get(1)._2().getPossibleTypes().contains("Integer"));

        Assert.assertEquals("YES", results.get(2)._2().getSample());
        Assert.assertEquals(1, results.get(2)._2().getPossibleTypes().size());
        Assert.assertTrue(results.get(2)._2().getPossibleTypes().contains("String"));
    }


}
