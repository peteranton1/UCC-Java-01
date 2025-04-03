package org.example.gatherer.ex02;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Gatherer;

public class MyMapMultiGatherer {
    // mapMulti(...) is a 1:N stateless,
    // parallelizable operation
    public <T, R> Gatherer<T, ?, R> mapMulti(
            BiConsumer<? super T,
                    Consumer<? super R>> mapper) {
        return Gatherer.of(
                (_, element, downstream) -> {
                    mapper.accept(element, downstream::push);
                    return !downstream.isRejecting();
                }
        );
    }
}
