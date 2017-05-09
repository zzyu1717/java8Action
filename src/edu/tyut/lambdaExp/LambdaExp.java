package edu.tyut.lambdaExp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @title LambdaExp.java
 * @description lambda表达、方法引用、函数式接口
 * @time 2017年5月9日下午6:19:03
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class LambdaExp {
	public static void main(String...args) {
		List<Apple> list = Arrays.asList(new Apple(30),new Apple(20),new Apple(35),new Apple(10));
		//1 匿名内部类实现排序
//		list.sort(new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1, Apple o2) {
//				return o1.compareTo(o2);
//			}
//		});
		// 2 lambda 表达式
//		list.sort((o1,o2)->o1.compareTo(o2));
		// 3 lambda 表达式
		list.sort(Comparator.comparing(o->o));
		// 4 方法应用
		list.forEach(System.out::println);
	}
}

class Apple implements Comparable<Apple> {
	private int weight;
	private String name = "apple";
	
	public Apple(int weight) {
		this.weight = weight;
	}
	public Apple() {}
	
	public String getName() {
		return name;
	}
	 
	@Override
	public String toString() {
		return this.weight + ", " + this.name;
	}
	@Override
	public int compareTo(Apple o) {
		if (this.weight > o.weight) {
			return 1;
		} else if (this.weight < o.weight) {
			return -1;
		} else {
			return 0;
		}
	}
}
 
