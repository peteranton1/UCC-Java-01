package org.example.gatherer.ex02;

import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class NamedGatherer {
    // Example showing how one could create a gatherer
    // as a named class. One can easily extract
    // the class outside of this class.
    public <T> Gatherer<T, ?, T> limit(long maxSize) {
        class Limit implements Gatherer<T, Limit.Count, T> {
            class Count { long left = maxSize; }
            @Override
            public Supplier<Count> initializer() {
                return Count::new;
            }

            @Override
            public Gatherer.Integrator<Count, T, T> integrator() {
                return (count, element, downstream) -> {
                    if (count.left <= 0)
                        return false;
                    count.left -= 1;
                    return downstream.push(element) && count.left > 0;
                };
            }
        }
        return new Limit();
    }
}
