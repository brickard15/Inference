package edu.murraystate.csis.inference.adapters.documents.json;

import edu.murraystate.csis.inference.adapters.documents.DocumentAdapter;
import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P2;

import java.io.Reader;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonDocumentAdapter extends DocumentAdapter<Reader> {
    public JsonDocumentAdapter(TypeTester tester) {
        super(tester);
    }

    @Override
    public Stream<P2<String, TestResult>> getSampleStream(Reader document) {
        return StreamSupport
                .stream(new JsonTupleIterable(document).spliterator(), false)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(tuple -> tuple.map2(TestResult::new));

    }
}
