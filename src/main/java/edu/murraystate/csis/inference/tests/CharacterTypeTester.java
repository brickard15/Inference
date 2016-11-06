package edu.murraystate.csis.inference.tests;

public final class CharacterTypeTester extends TryCatchTester<Character> {

    public CharacterTypeTester() {
        super(
            "Character", 
            (String input) -> {
                Character characterResult = ' ';
                final int characterLength = 1;

                if(input.length() == characterLength){
                    Character firstCharacter = input.charAt(0);
                    characterResult = firstCharacter;
                }

                return characterResult;    
            }
            );
    }
}
