package edu.murraystate.csis.inference.tests;

import java.util.HashSet;
import java.util.Set;

public class BooleanTypeTester implements TypeTester{
    private static final String typeString = "Boolean";
    
    @Override
    public TestResult test(final String sample) { 
        try{
            return parseBoolean(sample);
        }catch(Exception e){
            return new TestResult(sample);
        }
    }
    
    private TestResult parseBoolean(final String sample){
        if(sample.contentEquals("true") || sample.contentEquals("false")){
            Set<String> possibleTypes = new HashSet<>();
            possibleTypes.add(typeString);
            return new TestResult(possibleTypes, sample);
        }else{
            return new TestResult(sample);
        }
    }
}
