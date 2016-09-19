package edu.murraystate.csis.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Phillip Wright on 9/2/2016.
 */
public class RegularExpressionTester implements TypeTester {
    final String typeString;
    final Pattern pattern;

    public RegularExpressionTester(final String typeString, final String regex) {
        this.typeString = typeString;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public List<String> test(final String sample) {
        final List<String> result = new ArrayList<>();
        final Matcher m = pattern.matcher(sample);
        if (m.matches()) {
            result.add(typeString);
            return result;
        } else {
            return result;
        }
    }
}
