package edu.murraystate.csis.inference.tests;

public class ShortTypeTester extends TryCatchTester<Short>{  
    
    public ShortTypeTester() {
        super("Short", Short::parseShort);
    }
}