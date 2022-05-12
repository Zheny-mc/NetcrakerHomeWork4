package ru.netcraker.task2.c;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.netcraker.task2.b.TreeSetForTest;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestHashMap {
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestAdd(HashMapForTest set) {
        for (int i = 0; i < set.size; i++) {
            set.integers.put(i, "val" + i);
        }
        set.integers.clear();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestContainsKey(HashMapForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.containsKey(i);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestDelete(HashMapForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.remove(i);
        }
    }

    public static Map readTestData() {
        Map<Integer, String> integers = new HashMap<>();

        final int size = 1000;
        for (int i = 0; i < size; i++) {
            integers.put(i, "val" + i);
        }
        return integers;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestHashMap.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
