package edu.tyut.pattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title Debugging.java
 * @description peek()方法使用
 * @time 2017年5月16日下午3:10:52
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class Debugging {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(2,4,6,8);
		numbers.stream()
				.map(x->x+17)
				.filter(x -> x % 2 == 0)
				.limit(3)
				.forEach(System.out::println);
		// 使用日志调试
		List<Integer> result = numbers.stream()
										.peek(x -> System.out.println("***from stream x = " + x))
										.map(x -> x+10)
										.peek(x -> System.out.println("&&&after map x = " + x))
										.filter(x -> x % 2 == 0)
										.peek(x -> System.out.println("^^^after filter x = " + x))
										.limit(2)
										.peek(x -> System.out.println("###after limit x = " + x))
										.collect(Collectors.toList() );
		result.forEach(System.out::println);
	}
}
