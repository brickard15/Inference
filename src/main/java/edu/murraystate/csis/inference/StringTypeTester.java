package edu.murraystate.csis.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author pwright4
 */
public class StringTypeTester implements TypeTester {

    public StringTypeTester() {

    }

    @Override
    public List<String> test(final String sample) {
        final List<String> result = new ArrayList<>();
        result.add("String");
        return result;
    }

}
