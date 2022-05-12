package ru.netcraker.task2.b;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Set;
import java.util.TreeSet;

@State(Scope.Benchmark)
public class TreeSetForTest {
    final int size = 1000;
    Set<Integer> integers = new TreeSet<>();
}