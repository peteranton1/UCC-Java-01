package org.example.gatherer.ex02;

import java.util.function.Function;
import java.util.stream.Gatherer;

public class MyMapGatherer {
    // map(_) is a 1:1 stateless, parallelizable,
    // intermediate operation
    public <T, R> Gatherer<T, ?, R> map(
            Function<? super T, ? extends R> mapper) {
        return Gatherer.of(
                (_, element, downstream) ->
                        downstream.push(mapper.apply(element))
        );
    }
}
