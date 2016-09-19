package edu.murraystate.csis.inference;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phillip Wright on 9/19/2016.
 */
public class TypeTesterTest {
    @Test
    public void aggregateTest(){
        final TypeTester t = new DummyTest();
        final List<String> samples = new ArrayList<>();
        samples.add("input1");
        samples.add("input2");
        samples.add("input3");

        final List<List<String>> result = t.test(samples);
        Assert.assertEquals(3,result.size());
        for(final List<String> l : result){
            Assert.assertEquals(1,l.size());
            Assert.assertEquals("test",l.get(0));
        }
    }

    public class DummyTest implements TypeTester{

        @Override
        public List<String> test(String sample) {
            final List<String> result = new ArrayList<>();
            result.add("test");
            return result;
        }
    }
}
