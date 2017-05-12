package edu.tyut.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @title StreamTest.java
 * @description 因为集合是数据结构，所以它的主要目的是以特定的时间/空间复杂度存储和访问元素。而
 * 流的目的在于表达计算
 * @time 2017年5月11日下午12:53:39
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class StreamTest {
	public static void main(String...args) {
		List<Dish> menu = Arrays.asList(
				new Dish("pork",false,800,Dish.Type.MEAT),
				new Dish("beef",false,700,Dish.Type.MEAT),
				new Dish("chicken",false,400,Dish.Type.MEAT),
				new Dish("french fries", true, 530,Dish.Type.OTHER),
				new Dish("rice",true,120,Dish.Type.OTHER),
				new Dish("season fruit",true,120,Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns",false,300,Dish.Type.FISH),
				new Dish("salmon",false,450,Dish.Type.FISH) );
		
		//查找三个能量大于300的菜肴名称
	/*	List<String> threeHighCaloricDishNames = menu.stream().
				filter(d -> d.getCalories() > 300).map(Dish::getName).
				limit(3).collect(Collectors.toList());
		threeHighCaloricDishNames.forEach(System.out::println);*/
		// 流只能消费一次
	/*	List<String> title = Arrays.asList("Java8", "In", "Action");
		Stream<String> s = title.stream();
		s.forEach(System.out::println);*/
		//java.lang.IllegalStateException: stream has already been operated upon or closed
		//s.forEach(System.out::println);
		// 流的优化--利用延迟性质
	/*	List<String> names = menu.stream()
				.filter(d -> {System.out.println("filtering..." + d.getName());return d.getCalories() > 300;})
				.map(d -> {System.out.println("mapping..."+ d.getName());return d.getName();} )
				.limit(3)
				.collect(Collectors.toList());
		names.forEach(System.out::println);*/
		
		/*List<String> names = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.skip(2)
				.limit(3)
				.skip(1)
				.limit(1)
				.collect(Collectors.toList());
		names.forEach(System.out::println);*/
		/*List<Integer> dishNameLength = menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(Collectors.toList());
		System.out.println(dishNameLength);*/
		
	/*	String[] arrayOfWords = {"Goodbye", "World"};
		Stream<String> streamOfWords = Arrays
				.stream(arrayOfWords);
		streamOfWords.forEach(System.out::println);*/
		
//		String[] words = {"hello","world"};
//		List<String> wordsList = Arrays.asList(words);
		/*List<Stream<String>> result = wordsList.stream()
		.map(w -> w.split(""))
		.map(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());*/
		
		// 输出所有字符串中的所有字符，不包含重复的字符
		/*List<String> list = Arrays.asList("hello", "world");
		list.stream()
		.map(w->w.split(""))
		.flatMap(Arrays::stream)
		.distinct()
		.collect(Collectors.toList())
		.forEach(System.out::print);*/
		
		// [1,2,3,4,5]--->[1,4,9,16,25]
		/*List<Integer> list = Arrays.asList(1,2,3,4,5);
		list.stream()
		.map(n->n*n)
		.collect(Collectors.toList())
		.forEach(System.out::print);*/
		
		// [1,2,3] [3,4]--->(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)
		/*List<Integer> list1 = Arrays.asList(1,2,3);
		List<Integer> list2 = Arrays.asList(3,4);
		List<int[]> pairs = list1.stream()
								 .flatMap(i -> list2.stream()
								 .map(j->new int[]{i,j}))
								 .collect(Collectors.toList());
		pairs.forEach(a->System.out.println(Arrays.toString(a)));*/
		 
		/*for (int[] a : pairs) {
			System.out.println(Arrays.toString(a));
		}*/
		
		/*boolean isVegetarian = menu.stream().anyMatch(Dish::isVegetarian);
		if (isVegetarian) {
			System.out.println("The menu is somewhat vegetarian frendly!");
		}*/
		// Optional类，使用此类不用返回众所周知容易出问题的null，避免和null检查相关的bug
	/*	Optional<Dish> dish = menu.stream()
								  .filter(Dish::isVegetarian)
								  .findAny();
		 menu.stream()
		  .filter(Dish::isVegetarian)
		  .findAny()
		  .ifPresent(d -> System.out.println(d.getName()));*/
		// map-reduce模式
		menu.stream()
			.map(d-> 1)
			.reduce(Integer::sum)
			.ifPresent(System.out::println);
	}
}

class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public int getCalories() {
		return calories;
	}
	public Type getType() {
		return type;
	}
	@Override
	public String toString() {
		return name;
	}
	public enum Type {MEAT, FISH, OTHER};
	
}