package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pwright4 on 9/19/2016.
 */
public class CompositeTypeTesterTest {
    @Test
    public void simpleTest(){
        final List<String> returns = new ArrayList<>();
        returns.add("hello");
        returns.add("hola");
        returns.add("ola");

        final TypeTester dummy = sample -> {
            final List<String> result = new ArrayList<>();
            result.add(returns.get(0));
            returns.remove(0);
            return result;
        };

        final List<TypeTester> testers = new ArrayList<>();
        testers.add(dummy);
        testers.add(dummy);
        testers.add(dummy);

        final TypeTester composite = new CompositeTypeTester(testers);
        final List<String> result = composite.test("teststring");

        Assert.assertEquals(3,result.size());
        Assert.assertEquals(0,returns.size());
        Assert.assertEquals("hello",result.get(0));
        Assert.assertEquals("hola",result.get(1));
        Assert.assertEquals("ola",result.get(2));

    }


}
