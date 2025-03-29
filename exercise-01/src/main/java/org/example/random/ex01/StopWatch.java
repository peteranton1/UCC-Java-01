package org.example.random.ex01;

public class StopWatch {

    private long millisStart = 0L;
    private long millisFinish = 0L;

    public void start() {
        millisStart = System.nanoTime();
    }

    public void stop() {
        millisFinish = System.nanoTime();
    }

    public long diffTime() {
        return millisFinish - millisStart;
    }
}
