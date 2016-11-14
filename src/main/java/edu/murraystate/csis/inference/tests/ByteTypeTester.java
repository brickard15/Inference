package edu.murraystate.csis.inference.tests;

public class ByteTypeTester extends TryCatchTester<Byte>{

    public ByteTypeTester() {
        super("Byte", Byte::parseByte);
    }    
}
