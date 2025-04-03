package org.example.gatherer.ex02;

import java.util.stream.Gatherer;

public class MyLimitGatherer {
    // limit(...) is a 1:0..1 stateful,
    // sequential operation
    public <T> Gatherer<T, ?, T> limit(long maxSize) {
        class Count {
            long left = maxSize;
        }
        return Gatherer.ofSequential(
                Count::new,
                (count, element, downstream) -> {
                    if (count.left == 0)
                        return false;
                    count.left -= 1;
                    return downstream.push(element) &&
                            count.left > 0;
                }
        );
    }
}
