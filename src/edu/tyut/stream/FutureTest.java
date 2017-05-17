package edu.tyut.stream;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @title FutureTest.java
 * @description 并发，异步计算
 * @time 2017年5月17日下午12:36:25
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class FutureTest {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call()  {
				return doSomethingCompute();
			}
		});
		doSomethingElse();
		try {
			Integer result = future.get(1,TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private static void doSomethingElse() {
		System.out.println("hello world");
	}
	
	private static Integer doSomethingCompute() {
		int N = 100;
		return IntStream.rangeClosed(0,N).sum();
	}
}
