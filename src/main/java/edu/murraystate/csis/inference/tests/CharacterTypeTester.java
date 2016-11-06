package edu.murraystate.csis.inference.tests;

public class CharacterTypeTester extends TryCatchTester<Character> {

    public CharacterTypeTester() {
        super("Character", Character::parseCharacter);
    }
}
