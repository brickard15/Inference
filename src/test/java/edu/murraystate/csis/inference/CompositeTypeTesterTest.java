package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompositeTypeTesterTest {
    @Test
    public void simpleTest(){
        final List<String> returns = new ArrayList<>();
        returns.add("hello");
        returns.add("hola");
        returns.add("ola");

        final TypeTester dummy = sample -> {
            final Set<String> result = new HashSet<>();
            result.add(returns.get(0));
            returns.remove(0);
            return new TestResult(result);
        };

        final List<TypeTester> testers = new ArrayList<>();
        testers.add(dummy);
        testers.add(dummy);
        testers.add(dummy);

        final TypeTester composite = new CompositeTypeTester(testers);
        final TestResult result = composite.test("teststring");

        Assert.assertEquals(3, result.getPossibleTypes().size());
        Assert.assertEquals(0,returns.size());
        Assert.assertTrue(result.getPossibleTypes().contains("hello"));
        Assert.assertTrue(result.getPossibleTypes().contains("hola"));
        Assert.assertTrue(result.getPossibleTypes().contains("ola"));

    }


}
