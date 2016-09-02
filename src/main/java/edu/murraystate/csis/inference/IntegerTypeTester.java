package edu.murraystate.csis.inference;

import java.util.Optional;

/**
 * @author pwright4
 */
public class IntegerTypeTester implements TypeTester {

    public IntegerTypeTester() {

    }

    public Optional<String> test(final String sample) {
        try {
            Integer.parseInt(sample);
            return Optional.of("Integer");
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
