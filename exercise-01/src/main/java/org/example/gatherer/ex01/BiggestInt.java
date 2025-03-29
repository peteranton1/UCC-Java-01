package org.example.gatherer.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public record BiggestInt(int limit)
        implements Gatherer<Integer, List<Integer>, Integer>{

    // The initializer creates a new private
    // ArrayList to keep track of the
    // largest integer across elements.
    @Override
    public Supplier<List<Integer>> initializer() {
        return () -> new ArrayList<Integer>(1);
    }

    // The integrator
    @Override
    public Integrator<List<Integer>, Integer, Integer> integrator() {
        return Integrator.of(
                (max, element, downstream) -> {
                    // Save the integer if it's the largest so far.
                    if(max.isEmpty()) {
                        max.addFirst(element);
                    } else if (element > max.getFirst()) {
                        max.set(0, element);
                    }

                    // If the integer is equal or greater to the limit,
                    // "short-circuit"; emit the current integer
                    // downstream and return false to stop
                    // processing stream elements.
                    if (element >= limit) {
                        downstream.push(element);
                        return false;
                    }

                    // Return true to continue processing
                    // stream elements
                    return true;
                }
        );
    }

    // The combiner, which is used during parallel evaluation.
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (leftMax, rightMax) -> {
            // If either the "left" or "right" ArrayLists
            // contain no value, then return the other
            if (leftMax.isEmpty()) {
                return rightMax;
            }
            if (rightMax.isEmpty()) {
                return leftMax;
            }

            // Return the ArrayList that contains the larger integer
            int leftVal = leftMax.getFirst();
            int rightVal = rightMax.getFirst();
            if(leftVal > rightVal) {
                return leftMax;
            } else {
                return rightMax;
            }
        };
    }

    @Override
    public BiConsumer<List<Integer>, Downstream<? super Integer>> finisher() {
        // Emit the largest integer, if there is one, downstream.
        return (max, downstream) -> {
            if (!max.isEmpty()) {
                downstream.push(max.getFirst());
            }
        };
    }
}






