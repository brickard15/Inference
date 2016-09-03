package edu.murraystate.csis.inference;

import java.util.Optional;

/**
 * @author pwright4
 */
public interface TypeTester {
     Optional<String> test(final String sample);
}
