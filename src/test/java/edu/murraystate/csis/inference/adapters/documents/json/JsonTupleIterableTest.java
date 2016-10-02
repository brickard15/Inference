package edu.murraystate.csis.inference.adapters.documents.json;

import fj.P2;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Optional;

public class JsonTupleIterableTest {
    @Test
    public void simpleTest() {
        final String json = "{\"data\":33}";
        final JsonTupleIterable iterable = new JsonTupleIterable(new StringReader(json));
        final Iterator<Optional<P2<String, String>>> iterator = iterable.iterator();
        Assert.assertTrue(iterator instanceof JsonTupleIterator);
        final P2<String, String> next = iterator.next().get();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals("$.data", next._1());
        Assert.assertEquals("33", next._2());
    }
}
