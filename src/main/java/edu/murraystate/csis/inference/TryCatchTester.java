package edu.murraystate.csis.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Phillip Wright on 9/2/2016.
 */
public class TryCatchTester<T> implements TypeTester {
    private final Function<String, T> testFunction;
    private final String typeString;

    public TryCatchTester(final String typeString, Function<String, T> testFunction) {
        this.typeString = typeString;
        this.testFunction = testFunction;
    }

    @Override
    public List<String> test(final String sample) {
        try {
            testFunction.apply(sample);
            final List<String> result = new ArrayList<>();
            result.add(typeString);
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
