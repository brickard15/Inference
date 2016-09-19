package edu.murraystate.csis.inference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Phillip Wright
 */
public interface TypeTester {
     List<String> test(final String sample);

     default List<List<String>> test(final List<String> samples){
          final List<List<String>> result = new ArrayList<>();
          for(final String s : samples){
               result.add(test(s));
          }
          return result;
     }
}
