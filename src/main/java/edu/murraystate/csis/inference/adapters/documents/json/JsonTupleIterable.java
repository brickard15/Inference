package edu.murraystate.csis.inference.adapters.documents.json;

import fj.P2;

import java.io.Reader;
import java.util.Iterator;
import java.util.Optional;

public class JsonTupleIterable implements Iterable<Optional<P2<String, String>>> {
    private final Reader reader;

    public JsonTupleIterable(final Reader reader) {
        this.reader = reader;
    }

    @Override
    public Iterator<Optional<P2<String, String>>> iterator() {
        return new JsonTupleIterator(reader);
    }
}
