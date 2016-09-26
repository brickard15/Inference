package edu.murraystate.csis.inference.adapters;

public interface TestAdapter<T, U> {
    U test(final T input);
}
