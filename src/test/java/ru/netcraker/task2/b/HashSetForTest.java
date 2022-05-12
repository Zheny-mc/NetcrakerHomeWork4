package ru.netcraker.task2.b;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@State(Scope.Benchmark)
public class HashSetForTest {
    final int size = 1000;
    Set<Integer> integers = new HashSet<>();
}