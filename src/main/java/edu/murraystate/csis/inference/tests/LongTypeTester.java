package edu.murraystate.csis.inference.tests;

public class LongTypeTester extends TryCatchTester<Long> {
    public LongTypeTester() {
        super("Long", Long::parseLong);
    }
}
