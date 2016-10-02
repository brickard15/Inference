package edu.murraystate.csis.inference.adapters.documents.json;

import fj.P;
import fj.P2;
import org.junit.Test;

import java.io.StringReader;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JsonTupleIteratorTest {
    @Test
    public void simpleStringTest() {
        final String json = "{\"name\":\"Phillip\"}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.name", "Phillip"), tuple);
    }

    @Test
    public void simpleNumberTest() {
        final String json = "{\"age\":33}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.age", "33"), tuple);
    }

    @Test
    public void simpleBooleanTest() {
        final String json = "{\"male\":true}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.male", "true"), tuple);
    }

    @Test
    public void simpleArrayTest() {
        final String json = "{\"data\":[33]}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data[0]", "33"), tuple);
    }

    @Test
    public void simpleObjectTest() {
        final String json = "{\"data\":{\"age\":33}}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data.age", "33"), tuple);
    }

    @Test
    public void simpleNullTest() {
        final String json = "{\"data\":null}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data", "null"), tuple);
    }

    @Test
    public void deepObjectNestingTest() {
        final String json = "{\"data\":{\"level2\":{\"level3\":{\"value\":true}}}}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data.level2.level3.value", "true"), tuple);
    }

    @Test
    public void deepArrayNestingTest() {
        final String json = "{\"data\":[[[33]]]}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data[0][0][0]", "33"), tuple);
    }

    @Test
    public void deepObjectArrayNestingTest() {
        final String json = "{\"data\":[{\"level2\":[{\"level3\":[\"hello\"]}]}]}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final Optional<P2<String, String>> Optional = iterator.next();
        final P2<String, String> tuple = Optional.get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.data[0].level2[0].level3[0]", "hello"), tuple);
    }

    @Test
    public void simpleTestTwo() {
        final String json = "{\"name\":\"Phillip\",\"age\":33}";
        final JsonTupleIterator iterator = new JsonTupleIterator(new StringReader(json));
        final P2<String, String> first = iterator.next().get();
        final P2<String, String> second = iterator.next().get();
        assertFalse(iterator.hasNext());
        assertEquals(P.p("$.name", "Phillip"), first);
        assertEquals(P.p("$.age", "33"), second);
    }
}
