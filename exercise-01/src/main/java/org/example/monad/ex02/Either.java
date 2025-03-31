package org.example.monad.ex02;

import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Either<T> {
    static <T> Either<T> of(Supplier<T> supplier) {
        try {
            return new Right<T>(supplier.get());
        } catch (Exception e){
            return new Left<>(e);
        }
    }
    <R> Either<R> map(Function<T, R> fn) ;
    T orElse(T other);
}

record Left<T>(Exception e) implements Either<T> {
    @Override
    public <R> Either<R> map(Function<T, R> fn) {
        return new Left<>(e);
    }

    @Override
    public T orElse(T other) {
        return other;
    }
}
record Right<T>(T result) implements Either<T> {
    @Override
    public <R> Either<R> map(Function<T, R> fn) {
        try {
            return new Right<>(fn.apply(result));
        } catch (Exception e){
            return new Left<>(e);
        }
    }

    @Override
    public T orElse(T other) {
        return result;
    }
}
