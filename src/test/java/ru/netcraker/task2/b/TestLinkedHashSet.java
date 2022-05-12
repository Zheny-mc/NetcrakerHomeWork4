package ru.netcraker.task2.b;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestLinkedHashSet {
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestAdd(HashSetForTest set) {
        for (int i = 0; i < set.size; i++) {
            set.integers.add(i);
        }
        set.integers.clear();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestContains(LinkedHashSetForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.contains(i);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestDelete(LinkedHashSetForTest set) {
        set.integers = readTestData();

        for (int i = 0; i < set.size; i++) {
            set.integers.remove(i);
        }
    }

    public static Set readTestData() {
        File file = new File("testData.txt");
        Set<Integer> integers = new LinkedHashSet<>();

        try(Scanner in = new Scanner(new File("data/testData.txt"))){
            final int size = 1000;
            for (int i = 0; i < size; i++) {
                integers.add(in.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestLinkedHashSet.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
