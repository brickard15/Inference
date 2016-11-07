package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;

public final class CharacterTypeTester implements TypeTester {
    
    private final String typeString = "Character";

    @Override
    public TestResult test(String sample) {

        final int characterLength = 1;

        if(sample.length() == characterLength){
            final Set<String> result = new HashSet<>();
            result.add(typeString);
            return new TestResult(result, sample);
        }else{
            return new TestResult(sample);
        }
    }
}
