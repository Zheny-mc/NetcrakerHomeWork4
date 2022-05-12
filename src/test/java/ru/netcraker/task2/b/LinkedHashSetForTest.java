package ru.netcraker.task2.b;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.LinkedHashSet;
import java.util.Set;

@State(Scope.Benchmark)
public class LinkedHashSetForTest {
    final int size = 1000;
    Set<Integer> integers = new LinkedHashSet<>();
}