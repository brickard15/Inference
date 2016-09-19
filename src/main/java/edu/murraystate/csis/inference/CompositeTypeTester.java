package edu.murraystate.csis.inference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pwright4 on 9/19/2016.
 */
public class CompositeTypeTester implements TypeTester{
    private final List<TypeTester> testers;

    public CompositeTypeTester(final List<TypeTester> testers){
        this.testers = testers;
    }

    @Override
    public List<String> test(String sample) {
        final List<String> result = new ArrayList<>();
        for(final TypeTester t : testers){
            result.addAll(t.test(sample));
        }
        return result;
    }
}
