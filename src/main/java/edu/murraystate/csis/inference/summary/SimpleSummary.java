package edu.murraystate.csis.inference.summary;

import edu.murraystate.csis.inference.adapters.aggregation.StreamAdapter;
import edu.murraystate.csis.inference.tests.TestResult;
import edu.murraystate.csis.inference.tests.TypeTester;
import fj.P;
import fj.P2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleSummary {
    private final Map<String, Double> typeCounts = new HashMap<>();
    private int sampleCount = 0;
    private Stream<TestResult> testStream;

    public SimpleSummary(final TypeTester test, final List<String> samples) {
        this(new StreamAdapter(test), samples);
    }

    public SimpleSummary(final StreamAdapter test, final List<String> samples) {
        this(test, samples.stream());
    }

    public SimpleSummary(final TypeTester test, final Stream<String> samples) {
        this(new StreamAdapter(test), samples);
    }

    public SimpleSummary(final StreamAdapter test, final Stream<String> samples) {
        final Stream<TestResult> resultStream =
                samples.map(sample -> new TestResult(sample));
        testStream = test.test(resultStream);
    }

    public List<P2<String, Double>> getEstimates() {
        testStream.forEach(result -> {
            sampleCount++;
            for (final String type : result.getPossibleTypes()) {
                if (typeCounts.containsKey(type)) {
                    final double currentCount = typeCounts.get(type);
                    typeCounts.put(type, currentCount + 1);
                } else {
                    typeCounts.put(type, 1.0);
                }
            }
        });

        return typeCounts.entrySet()
                .stream()
                .map(entry -> P.p(entry.getKey(), entry.getValue() / sampleCount))
                .sorted((x, y) -> x._2().compareTo(y._2()))
                .collect(Collectors.toList());
    }
}
