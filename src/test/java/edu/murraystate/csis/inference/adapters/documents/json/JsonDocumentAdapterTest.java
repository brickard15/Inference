package edu.murraystate.csis.inference.adapters.documents.json;

import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P2;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonDocumentAdapterTest {
    @Test
    public void simpleTest() {
        final String json = "{\"data\":33}";
        final TypeTester tester = sample -> {
            final Set<String> possibleTypes = new HashSet<>();
            possibleTypes.add("testresult");
            return new TestResult(possibleTypes, sample);
        };
        final JsonDocumentAdapter docAdapter = new JsonDocumentAdapter(tester);
        final Stream<P2<String, TestResult>> result = docAdapter.test(new StringReader(json));
        final List<P2<String, TestResult>> collected = result.collect(Collectors.toList());
        Assert.assertEquals(1, collected.size());
        final P2<String, TestResult> first = collected.get(0);
        Assert.assertEquals("$.data", first._1());
        Assert.assertEquals("33", first._2().getSample());
        final Set<String> possibleTypes = first._2().getPossibleTypes();
        Assert.assertEquals(1, possibleTypes.size());
        Assert.assertTrue(possibleTypes.contains("testresult"));

    }
}
