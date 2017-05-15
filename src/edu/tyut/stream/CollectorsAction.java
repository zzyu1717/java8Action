package edu.tyut.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @title CollectorsAction.java
 * @description 收集器，主要提供了三大功能：1、将流元素归约和汇总为一个值；2、元素分组；3、元素分区
 * @time 2017年5月13日下午3:45:20
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class CollectorsAction {
	public static void main(String[] args) {
		// 菜单实例
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
		
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		// 交易实例
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian,2011,300),
				new Transaction(raoul,2012,1000),
				new Transaction(raoul,2011,400),
				new Transaction(mario,2012,710),
				new Transaction(mario,2012,700),
				new Transaction(alan,2012,950)
				);
		
//		long howManyDishes = menu.stream().collect(Collectors.counting());
//		long howManyDishes = menu.stream().count();
//		System.out.println(howManyDishes);
		/**
		 * 查找流中最大值和最小值
		 */
//		menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);;
		/**
		 * 汇总
		 */
		// 计算总卡路里量
//		int toalCalories = menu.stream().collect(summingInt(Dish::getCalories));
//		System.out.println(toalCalories);
		// 计算卡路里平均值
//		double avergeCalories = menu.stream().collect(averagingInt(dish -> dish.getCalories()));
//		System.out.println(avergeCalories);
		// 通过summarizingInt 取得元素个数、总值、最大值、最小值、平均值
//		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
//		System.out.println(menuStatistics);
		// 连接字符串
//		String name = menu.stream().map(Dish::getName).collect(joining(","));
//		String namefix = menu.stream().map(Dish::getName).collect(joining(",","*","#"));
//		System.out.println(name);
//		System.out.println(namefix);
		/**
		 * 广义的归约汇总---reducing
		 */
		// 总热量
//		int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories,(i,j)->i+j));
//		System.out.println(totalCalories);
		// 热量最高的菜encounter
//		Optional<Dish> mostCalories = menu.stream().collect(reducing((d1,d2)->d1.getCalories() > d2.getCalories() ? d1 : d2));
//		System.out.println(mostCalories);
		// 与Stream中reduce方法的不同
//		Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
//		List<Integer> numbers = stream.reduce(new ArrayList<Integer>(),
//				(List<Integer> l, Integer e) -> {
//					l.add(e);
//					return l;
//				}, (List<Integer> l1, List<Integer> l2) -> {
//					l1.addAll(l2);
//					return l1;
//				});
//		numbers.forEach(i -> System.out.print(i + " "));
		/**
		 * 分组
		 */
		// 按类型
//		Map<Dish.Type,List<Dish>> dishesByType= menu.stream().collect(Collectors.groupingBy(Dish::getType));
//		System.out.println(dishesByType);
		// 按卡路里
//		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish -> {
//			if (dish.getCalories() <= 400) {
//				return CaloricLevel.DIET;
//			} else if (dish.getCalories() <= 700) {
//				return CaloricLevel.NORMAL;
//			} else {
//				return CaloricLevel.FAT;
//			}
//		}));
//		System.out.println(dishesByCaloricLevel);
		// 二级分组
//		Map<Dish.Type, Map<CaloricLevel,List<Dish>>> dishesByTypeCaloric =  menu.stream().collect(Collectors.groupingBy(Dish::getType, 
//				Collectors.groupingBy(dish -> {
//			if (dish.getCalories() <= 400) {
//				return CaloricLevel.DIET;
//			} else if (dish.getCalories() <= 700) {
//				return CaloricLevel.NORMAL;
//			} else {
//				return CaloricLevel.FAT;
//			}
//		})));
//		System.out.println(dishesByTypeCaloric);
		
		// 把收集器的结果转换为另一种类型
//		Map<Dish.Type, Dish> maxCaloriesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, 
//				 Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get) ));
//		System.out.println(maxCaloriesByType);
		
		// groupingBy 与 mapping 联合使用
		/**
		 * mapping方法接收两个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来
		 */
//		Map<Dish.Type, Set<CaloricLevel>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
//					if (dish.getCalories() <= 400) {
//						return CaloricLevel.DIET;
//					} else if (dish.getCalories() <= 700) {
//						return CaloricLevel.NORMAL;
//					} else {
//						return CaloricLevel.FAT;
//					}
//				}, Collectors.toCollection(HashSet::new)) ));
//		System.out.println(map);
		/**
		 * 分区
		 */
		// 素食
//		Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
//		System.out.println(map);
		// 列出素食和非素食
//		Map<Boolean, Map<Dish.Type, List<Dish>>> map =  menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
//		System.out.println(map);
		
		// 将数字分为质数与非质数
//		Map<Boolean,List<Integer>> map = partitionPrime(20);
//		System.out.println(map);
		
		/**
		 * 自定义收集器，实现Collector接口
		 */
//		List<Dish> list = menu.stream().collect(new ToListCollector<Dish>());
//		System.out.println(list);
		/**
		 * 自定义收集器，不实现Collector接口
		 */
//		menu.stream().collect(ArrayList<Dish>::new, List::add, List::addAll).forEach(dish -> System.out.print(dish + " "));
		/**
		 * 开发自己的收集器，以获得更好的性能
		 */
//		Map<Boolean,List<Integer>> map = partitionPrimeWithCustomCollector(20);
//		System.out.println(map);
		
		// 性能比较,利用partioningBy方法最快510ms,自定义最快313ms,性能提升约38%
		System.out.println(runTime());
		
		
	}
	
	
	
	public static Map<Boolean,List<Integer>> partitionPrime(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(CollectorsAction::isPrime));
	}
	
	public static boolean isPrime(int candidate) {
		int candidateRoot = (int)Math.sqrt(candidate);
		return 	IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}
	// 仅用质数做除数
	public static boolean isPrime(List<Integer> prime, int candidate) {
		int candidateRoot = (int)Math.sqrt(candidate);
		return takeWhile(prime, i -> i <= candidateRoot).stream().noneMatch(j -> candidate % j == 0);
	}
	// 选用小于被测数平方根的质数进行测试
	public static  <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
		int i = 0;
		for (A a : list) {
			if (!predicate.test(a)) {
				return list.subList(0, i);
			}
			i ++;
		}
		return list;
	}
	
	public static Map<Boolean, List<Integer>> partitionPrimeWithCustomCollector(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
	}
	
	/**
	 * 比较收集器的性能
	 */
	public static String runTime() {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			//partitionPrime(1_000_000);
			partitionPrimeWithCustomCollector(1_000_000);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) 
				fastest = duration;
		}
		return "Fastest execution done in " + fastest + " miliseconds";
	}
	
	/*
	 * 将热量不到400卡路里的菜划分为低热量(diet)，
	 * 热量400到700卡路里的菜化为普通(normal)，
	 * 高于700卡路里的化为高热量(fat).
	 */
	static enum CaloricLevel {DIET,NORMAL,FAT}
}

/**
 * 定义Collector类的签名
 * @author ZZY
 *
 */
class PrimeNumbersCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
//		return () -> {
//			Map<Boolean, List<Integer>> map = new HashMap<>();
//			map.put(true, new ArrayList<Integer>());
//			map.put(false, new ArrayList<Integer>());
//			return map;
//		};
		return () -> new HashMap<Boolean,List<Integer>>() {
			{
				put(true,new ArrayList<Integer>());
				put(false,new ArrayList<Integer>());
			}
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean,List<Integer>> acc, Integer candidate) -> {
			acc.get(CollectorsAction.isPrime(acc.get(true), candidate)).add(candidate);
		};
	}
	
	// 此处不能并行工作
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean,List<Integer>> map1, Map<Boolean,List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
	}
	
}






/**
 * 实现Collector接口，将Stream<T>中的所有元素收集到一个List<T>里面
 * @author ZZY
 *
 * @param <R>
 */
class ToListCollector<T> implements Collector<T,List<T>,List<T>> {
	/**
	 * 1、建立新的结果容器--supplier方法
	 */
	@Override
	public Supplier<List<T>> supplier() {
//		Supplier<List<T>> supplier = new Supplier<List<T>>() { // 使用内部类实现
//			@Override
//			public List<T> get() {
//				List<T> list = new ArrayList<>();
//				return list;
//			}
//			
//		};
//		return supplier;
//		return () ->  new ArrayList<T>(); // 使用lambda表达式
		return ArrayList<T>::new;	// 使用构造函数引用
	}
	/**
	 * 2、将元素添加到结果容器--acccumulator方法
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
//		return (list, item)->list.add(item);
		return List::add;
	}
	
	/**
	 * 3、对结果容器应用最终转换：finisher方法
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}
	/**
	 * 4、合并两个结果容器：combiner方法
	 * 
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (l1,l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}
	
}

 






















