package edu.tyut.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @title IntStreamAction.java
 * @description TODO
 * @time 2017年5月12日下午4:47:59
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class IntStreamAction {
	public static void main(String...args) {
		/*IntStream evenNumbers = IntStream.rangeClosed(1, 100)
		 .filter(n -> n%2==0);
		evenNumbers.forEach(System.out::println);*/

		// 生成(a,b)1~100之间的勾股数(a,b,c)
		/*Stream<int[]> triples = IntStream.rangeClosed(1, 100)
		.boxed()
		.flatMap(a -> IntStream.rangeClosed(a, 100)
		  .filter(b->Math.sqrt(a*a+b*b)%1 == 0)
		  .mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a*a+b*b)})
		);
		triples.forEach(a -> System.out.println(Arrays.toString(a)));*/
		
		IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
									.mapToObj(b -> new double[] {a,b,Math.sqrt(a*a+b*b)})
									.filter(c -> c[2] % 1 == 0)
						).forEach(a -> System.out.println(Arrays.toString(a)));
	}
}
