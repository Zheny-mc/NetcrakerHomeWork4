package ru.netcraker.task2.c;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestLinkedHashMap {
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestAdd(LinkedHashMapForTest set) {
        for (int i = 0; i < set.size; i++) {
            set.integers.put(i, "val" + i);
        }
        set.integers.clear();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestContainsKey(LinkedHashMapForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.containsKey(i);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestDelete(LinkedHashMapForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.remove(i);
        }
    }

    public static Map readTestData() {
        Map<Integer, String> integers = new LinkedHashMap<>();

        final int size = 1000;
        for (int i = 0; i < size; i++) {
            integers.put(i, "val" + i);
        }
        return integers;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestLinkedHashMap.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
