package edu.murraystate.csis.inference;

/**
 *
 * @author pwright4
 */
public class IntegerTypeTester implements TypeTester{

    public boolean test(final String sample) {
        try{
            Integer.parseInt(sample);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
