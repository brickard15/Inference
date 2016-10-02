package edu.murraystate.csis.inference.adapters.documents.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import fj.P;
import fj.P2;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Optional;

public class JsonTupleIterator implements Iterator<Optional<P2<String, String>>> {
    private final JsonReader reader;

    public JsonTupleIterator(final Reader reader) {
        this.reader = new JsonReader(reader);
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.hasNext();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Optional<P2<String, String>> next() {
        try {
            final Optional<String> maybePath = getNextPath();
            final Optional<String> maybeValue = getNextValue();
            if (maybePath.isPresent() && maybeValue.isPresent()) {
                final P2<String, String> tuple = P.p(maybePath.get(), maybeValue.get());
                return Optional.of(tuple);
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<String> getNextPath() throws IOException {
        final JsonToken peeked = reader.peek();
        if (isValue(peeked)) {
            return Optional.of(reader.getPath());
        } else if (peeked.equals(JsonToken.NAME)) {
            reader.nextName();
            final String currentPath = reader.getPath();
            final JsonToken lookahead = reader.peek();
            if (lookahead.equals(JsonToken.BEGIN_OBJECT) || lookahead.equals(JsonToken.BEGIN_ARRAY)) {
                return getNextPath();
            } else {
                return Optional.of(currentPath);
            }
        } else if (peeked.equals(JsonToken.BEGIN_OBJECT)) {
            reader.beginObject();
            return getNextPath();
        } else if (peeked.equals(JsonToken.END_OBJECT)) {
            reader.endObject();
            return getNextPath();
        } else if (peeked.equals(JsonToken.BEGIN_ARRAY)) {
            reader.beginArray();
            return getNextPath();
        } else if (peeked.equals(JsonToken.END_ARRAY)) {
            reader.endArray();
            return getNextPath();
        } else {
            return Optional.empty();
        }
    }

    private boolean isValue(final JsonToken token) {
        return token.equals(JsonToken.NUMBER)
                || token.equals(JsonToken.STRING)
                || token.equals(JsonToken.BOOLEAN)
                || token.equals(JsonToken.NULL);
    }

    private Optional<String> getNextValue() throws IOException {
        final JsonToken peeked = reader.peek();
        if (peeked.equals(JsonToken.BEGIN_OBJECT)) {
            reader.beginObject();
            return getNextValue();
        } else if (peeked.equals(JsonToken.END_OBJECT)) {
            reader.endObject();
            return getNextValue();
        } else if (peeked.equals(JsonToken.BEGIN_ARRAY)) {
            reader.beginArray();
            return getNextValue();
        } else if (peeked.equals(JsonToken.END_ARRAY)) {
            reader.endArray();
            return getNextValue();
        } else if (peeked.equals(JsonToken.STRING) || peeked.equals(JsonToken.NUMBER)) {
            return Optional.of(reader.nextString());
        } else if (peeked.equals(JsonToken.BOOLEAN)) {
            return Optional.of(Boolean.toString(reader.nextBoolean()));
        } else if (peeked.equals(JsonToken.NULL)) {
            reader.nextNull();
            return Optional.of("null"); // TODO this seems quite terrible...
        } else {
            return Optional.empty();
        }
    }
}
