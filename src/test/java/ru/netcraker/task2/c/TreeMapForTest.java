package ru.netcraker.task2.c;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@State(Scope.Benchmark)
public class TreeMapForTest {
    final int size = 1000;
    Map<Integer, String> integers = new TreeMap<>();
}