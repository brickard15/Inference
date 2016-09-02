package edu.murraystate.csis.inference;

/**
 * Created by Phillip Wright on 9/2/2016.
 */
public class LongTypeTester extends TryCatchTester<Long> {
    public LongTypeTester() {
        super("Long", Long::parseLong);
    }
}
