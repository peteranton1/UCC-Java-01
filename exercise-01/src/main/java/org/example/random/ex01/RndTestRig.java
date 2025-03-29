package org.example.random.ex01;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RndTestRig {

    public record RndCase(String name,
                          long averageTime,
                          int bound,
                          int nums,
                          int runs) {
    }

    public RndCase timerTest(RndCase rndCase) {

        RandomGeneratorFactory<RandomGenerator> factory =
                RandomGeneratorFactory.of(rndCase.name);
        RandomGenerator random = factory.create();

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < rndCase.runs; i++) {
            for (int j = 0; j < rndCase.nums; j++) {
                random.nextInt(rndCase.bound);
            }
        }
        sw.stop();
        long avgTime = sw.diffTime() / rndCase.runs;

        return new RndCase(rndCase.name,
                avgTime,
                rndCase.bound,
                rndCase.nums,
                rndCase.runs);
    }
}
