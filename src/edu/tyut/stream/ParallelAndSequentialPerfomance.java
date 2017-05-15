package edu.tyut.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @title ParallelStreamAction.java
 * @description 选择适当的数据结构往往比并行算法更重要
 * @time 2017年5月15日上午11:34:25
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class ParallelAndSequentialPerfomance {
	public static void main(String...args) {
		long n = 10_000_000;
		// 120ms
		System.out.println("sequential sum done in " + measureSumPrefermance(i -> sequentialSum(i), n) + " miliseconds");
		// 5ms
		System.out.println("sequential range sum done in " + measureSumPrefermance(ParallelAndSequentialPerfomance::sequentialRangedSum, n) + " miliseconds");
		// 8ms
		System.out.println("iterative sum done in " + measureSumPrefermance(ParallelAndSequentialPerfomance::iterativeSum, n) + " miliseconds");
		// 145ms
		System.out.println("parallel sum done in " + measureSumPrefermance(ParallelAndSequentialPerfomance::ParallelSum, n) + " miliseconds");
		// 5ms
		System.out.println("parallel range sum done in " + measureSumPrefermance(ParallelAndSequentialPerfomance::parallelRangedSum, n) + " miliseconds");
	}
	
	/**
	 * 测量流执行性能
	 * @param adder
	 * @param n
	 * @return
	 */
	public static long measureSumPrefermance(Function<Long,Long> adder, long n) {
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
	
	public static long sequentialSum(long n)  {
		return Stream.iterate(1L, i -> i+1).limit(n).reduce(0L, Long::sum);
	}
	public static long iterativeSum(long n) {
		long result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}
	public static long ParallelSum(long n) {
		return Stream.iterate(0L, i -> i = i+1).limit(n).parallel().reduce(0L, (p,q)-> p+q);
	}
	// 顺序实现没有装箱和拆箱操作
	public static long sequentialRangedSum(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}
	// 并行实现没有装箱和拆箱操作
	public static long parallelRangedSum(long n) {
		return LongStream.range(1, n).reduce(0L, Long::sum);
	}
}
