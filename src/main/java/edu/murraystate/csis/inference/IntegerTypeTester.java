package edu.murraystate.csis.inference;

public class IntegerTypeTester extends TryCatchTester<Integer> {

    public IntegerTypeTester() {
        super("Integer", Integer::parseInt);
    }
}
