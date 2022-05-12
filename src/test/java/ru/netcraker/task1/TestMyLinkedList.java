package ru.netcraker.task1;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.netcraker.my_linkedList.MylinkeList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class TestMyLinkedList {

	@Benchmark
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void testAdd(MyListForTest listForTest) {
		for (int i = 0; i < listForTest.size; i++) {
			listForTest.integers.add(i);
		}
		listForTest.integers.clear();
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void testUpdate(MyListForTest listForTest) {
		listForTest.integers = readTestData();

		for (int i = 0; i < listForTest.size; i++) {
			listForTest.integers.set(i, i+10);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void testDelete(MyListForTest listForTest) {
		listForTest.integers = readTestData();

		for (int i = 0; i < listForTest.size; i++) {
			listForTest.integers.remove(0);
		}
	}

	public static void createTestData() {
		try(FileWriter writer = new FileWriter("data/testData.txt", true)) {
			final int size = 1000;
			for (int i = 0; i < size; i++) {
				writer.write(String.format("%d ", i));
			}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}

	public static MylinkeList readTestData() {
		File file = new File("testData.txt");
		MylinkeList<Integer> integers = new MylinkeList<>();

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
				.include(TestMyLinkedList.class.getSimpleName())
				.forks(1)
				.build();
		new Runner(opt).run();
	}
}