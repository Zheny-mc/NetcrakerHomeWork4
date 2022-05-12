package ru.netcraker.task2.a;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@State(Scope.Benchmark)
public class ArrayListForTest {
    final int size = 1000;
    List<Integer> integers = new ArrayList<>();
}