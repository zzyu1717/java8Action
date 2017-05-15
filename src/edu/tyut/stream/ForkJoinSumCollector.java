package edu.tyut.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * @title ForkJoinSumCollector.java
 * @description 分支/合并
 * @time 2017年5月15日下午3:39:12
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class ForkJoinSumCollector extends RecursiveTask<Long> {
	private final long[] numbers;
	private final int start;
	private final int end;

	public static final long THRESHOLD = 10_1000;

	public ForkJoinSumCollector(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	public ForkJoinSumCollector(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	@Override
	protected Long compute() {
		int length = end - start;
		if (length <= THRESHOLD) {
			return computeSequentially();
		}
		ForkJoinSumCollector leftTask = new ForkJoinSumCollector(numbers, start, start + length / 2);
		leftTask.fork();
		ForkJoinSumCollector rightTask = new ForkJoinSumCollector(numbers, start + length / 2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = leftTask.join();
		return rightResult + leftResult;
	}

	private Long computeSequentially() {
		long result = 0;
		for (int i = start; i < end; i++) {
			result += i;
		}
		return result;
	}

	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(0, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCollector(numbers);
		return new ForkJoinPool().invoke(task);
	}

	public static long measureSumPrefermance(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}
	
	public static void main(String[] args) {
		long n = 10_000_000;
		// 62ms
		System.out.println("fork/join sum in " + measureSumPrefermance(ForkJoinSumCollector::forkJoinSum, n) + " ms");
	}
}
