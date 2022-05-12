package ru.netcraker.task2.c;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@State(Scope.Benchmark)
public class LinkedHashMapForTest {
    final int size = 1000;
    Map<Integer, String> integers = new LinkedHashMap<>();
}