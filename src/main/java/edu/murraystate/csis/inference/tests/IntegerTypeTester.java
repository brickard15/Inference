package edu.murraystate.csis.inference.tests;

public class IntegerTypeTester extends TryCatchTester<Integer> {

    public IntegerTypeTester() {
        super("Integer", Integer::parseInt);
    }
}
