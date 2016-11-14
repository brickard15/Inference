package edu.murraystate.csis.inference.tests;

public class DoubleTypeTester extends TryCatchTester<Double>{
    
    public DoubleTypeTester() {
        super("Double", Double::parseDouble);
    }
}
