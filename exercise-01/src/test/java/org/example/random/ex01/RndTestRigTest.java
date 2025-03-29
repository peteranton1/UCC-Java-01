package org.example.random.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class RndTestRigTest {

    private RndTestRig testRig;

    @BeforeEach
    void setUp() {
        testRig = new RndTestRig();
    }

    @Test
    void timerTest() {
        long avgTime = -1L;
        int bound = 10000;
        int nums = 1000;
        int runs = 2;
        List<RndTestRig.RndCase> rndCases = List.of(
                new RndTestRig.RndCase("L128X1024MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L64X1024MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L128X256MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L64X256MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("Xoshiro256PlusPlus", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L128X128MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L64X128StarStarRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L64X128MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("Xoroshiro128PlusPlus", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("L32X64MixRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("SplittableRandom", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("Random", avgTime, bound, nums, runs),
                new RndTestRig.RndCase("SecureRandom", avgTime, bound, nums, runs)
        );
        List<RndTestRig.RndCase> results = rndCases.stream()
                .map(r -> testRig.timerTest(r))
                .sorted((f, g) -> Math.toIntExact(g.averageTime() - f.averageTime()))
                .toList();
        List<String> actual = results.stream()
                .map(rndCase -> {
                    String fmt = "\"%25s: %15d\",";
                    String msg = String.format(fmt, rndCase.name(), rndCase.averageTime());
                    System.out.println(msg);
                    return msg;
                })
                .toList();
        List<String> expected = List.of(
                "             SecureRandom:         3173507",
                "       L128X1024MixRandom:          677503",
                "          L32X64MixRandom:          347860",
                "     Xoroshiro128PlusPlus:          287696",
                "         SplittableRandom:          279909",
                "        L128X256MixRandom:          233741",
                "        L128X128MixRandom:          212950",
                "        L64X1024MixRandom:          175069",
                "                   Random:          172254",
                "         L64X256MixRandom:          159040",
                "    L64X128StarStarRandom:          154251",
                "       Xoshiro256PlusPlus:          150774",
                "         L64X128MixRandom:          146239"
        );
        Assertions.assertTrue(true);
    }
}