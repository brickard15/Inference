package edu.murraystate.csis.inference;

public class LongTypeTester extends TryCatchTester<Long> {
    public LongTypeTester() {
        super("Long", Long::parseLong);
    }
}
