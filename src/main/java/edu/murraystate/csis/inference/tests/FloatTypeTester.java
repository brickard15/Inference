package edu.murraystate.csis.inference.tests;

public class FloatTypeTester extends TryCatchTester<Float>{

    public FloatTypeTester() {
        super("Float", Float::parseFloat);
    }
}
