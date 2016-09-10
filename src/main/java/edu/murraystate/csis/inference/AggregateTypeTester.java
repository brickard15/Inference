package edu.murraystate.csis.inference;

import fj.P;
import fj.P2;

import java.util.*;

/**
 * Created by Phillip Wright on 9/2/2016.
 */
public class AggregateTypeTester {

    private final List<TypeTester> tests;

    public AggregateTypeTester(final List<TypeTester> tests) {
        this.tests = tests;
    }

    public List<P2<String, Double>> test(Collection<String> samples) {
        final Map<String, Integer> data = new HashMap<>();

        for (final String sample : samples) {
            for (final TypeTester t : tests) {
                t.test(sample).ifPresent(type -> {
                    if (data.containsKey(type)) {
                        int currentCount = data.get(type);
                        data.put(type, currentCount + 1);
                    } else {
                        data.put(type, 1);
                    }
                });
            }
        }

        final List<P2<String, Double>> results = new ArrayList<>();
        for (final String type : data.keySet()) {
            double percentage = data.get(type) / (double) samples.size();
            results.add(P.p(type, percentage));
        }

        Collections.sort(results, (r1, r2) -> Double.compare(r2._2(), r1._2()));

        return results;
    }
}
