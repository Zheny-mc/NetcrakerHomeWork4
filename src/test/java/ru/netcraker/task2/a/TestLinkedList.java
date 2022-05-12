package ru.netcraker.task2.a;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestLinkedList {
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestAdd(ListForTest listForTest) {
        for (int i = 0; i < listForTest.size; i++) {
            listForTest.integers.add(i);
        }
        listForTest.integers.clear();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestUpdate(ListForTest listForTest) {
        listForTest.integers = readTestData();

        for (int i = 0; i < listForTest.size; i++) {
            listForTest.integers.set(i, i+10);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void sourceTestDelete(ListForTest listForTest) {
        listForTest.integers = readTestData();

        for (int i = 0; i < listForTest.size; i++) {
            listForTest.integers.remove(0);
        }
    }

    public static List readTestData() {
        File file = new File("testData.txt");
        List<Integer> integers = new LinkedList<>();

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
                .include(TestLinkedList.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
