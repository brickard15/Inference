package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;

public class BooleanTypeTester implements TypeTester{
    final String typeString = "Boolean";

    @Override
    public TestResult test(String sample) {
        if("true".equals(sample) || "false".equals(sample)){
            Set<String> possibleTypes = new HashSet<>();
            possibleTypes.add(sample);
            return new TestResult(possibleTypes, sample);
        }else{
            return new TestResult(sample);
        }
    }
}
