package edu.tyut.stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @title CreateStream.java
 * @description TODO
 * @time 2017年5月12日下午6:56:02
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class CreateStream {
	public static void main(String...args) {
		/**
		 * 1 由值创建流
		 */
//		Stream<String> stream  = Stream.of("Java8","In", "Action");
//		stream.map(String::toUpperCase).forEach(System.out::println);
		// 创建以一个空流
//		Stream<String> emptyStream = Stream.empty();
		/**
		 * 2 由数组创建流
		 */
//		int[] numbers = {2,3,1,4,5};
//		int sum = Arrays.stream(numbers).sum();
//		System.out.println(sum); // 15
		/**
		 * 3 由文件生成流
		 */
		// 查看一个文件中有多少各不相同的词
//		long uniqueWords = 0;
//		try (Stream<String> lines = Files.lines(Paths.get("G:\\myeclipse\\SSH\\Java8Action\\src\\edu\\tyut\\resources\\data.txt"))) {
//			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();					                                                     
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(uniqueWords);
		/**
		 * 4 由函数生成流：创建无线流（Stream.iterate()和Stream.generate()）
		 */
		//(1) 迭代
		//Stream.iterate(0, n->n+2).limit(10).forEach(System.out::println);
//		// 生成斐波那契 元组序列：(0,1),(1,1),(1,2),(2,3),(3,5),(5,8),(8,13)
//		Stream.iterate(new int[]{0,1}, a -> new int[] {a[1],a[0]+a[1]})
//			  .limit(20)
//			  .forEach(t -> System.out.println("("+t[0] + "," + t[1] + ")"));
		// 生成斐波那契前二十个元素--无状态
//		Stream.iterate(new int[]{0,1}, a -> new int[] {a[1],a[0]+a[1]})
//		  .limit(20)
//		  .map(b -> b[0])
//		  .forEach(t -> System.out.print(t+" "));
		
		// (2) 生成
//		Stream.generate(Math::random).limit(5).forEach(System.out::println);
		// 生成斐波那契前二十个元素--有状态
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;
			@Override
			public int getAsInt() {
				int OldValue =  previous;
				int nextValue = previous + current;
				previous = current;
				current = nextValue;
				return OldValue;
			}
			
		};
		IntStream.generate(fib).limit(10).forEach(i -> System.out.print(i + " "));
	}
}
