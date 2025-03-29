package org.example.random.ex01;

import java.math.BigInteger;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Stream;

public class RndFactory {

    public record Rnd(String name, BigInteger period) {
    }

    public Stream<Rnd> listAll() {
        return RandomGeneratorFactory.all()
                .map((frg -> {
                    return new Rnd(frg.name(), frg.period());
                }));
    }

    public Stream<Rnd> listAllSorted() {
        return listAll()
                .sorted((f, g) -> g.period().compareTo(f.period()));
    }

}
