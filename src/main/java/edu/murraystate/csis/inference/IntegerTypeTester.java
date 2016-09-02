package edu.murraystate.csis.inference;

/**
 * @author pwright4
 */
public class IntegerTypeTester extends TryCatchTester<Integer> {

    public IntegerTypeTester() {
        super("Integer", Integer::parseInt);
    }
}
