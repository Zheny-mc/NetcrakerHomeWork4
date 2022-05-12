package ru.netcraker.task1;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import ru.netcraker.my_linkedList.MylinkeList;

@State(Scope.Benchmark)
public class MyListForTest {
    final int size = 1000;
    MylinkeList<Integer> integers = new MylinkeList<>();
}