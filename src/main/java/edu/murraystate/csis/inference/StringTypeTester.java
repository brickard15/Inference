package edu.murraystate.csis.inference;

import java.util.Optional;

/**
 * @author pwright4
 */
public class StringTypeTester implements TypeTester {

    public StringTypeTester() {

    }

    public Optional<String> test(final String sample) {
        return Optional.of("String");
    }

}
