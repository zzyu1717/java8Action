package edu.tyut.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

/**
 * @title StreamAction.java
 * @description TODO
 * @time 2017年5月12日下午12:40:39
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class StreamAction {
	public static void main(String...args) {
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian,2011,300),
				new Transaction(raoul,2012,1000),
				new Transaction(raoul,2011,400),
				new Transaction(mario,2012,710),
				new Transaction(mario,2012,700),
				new Transaction(alan,2012,950)
				);
		/**
		 * (1)  找出2011年发生的所有交易，并按交易额排序（从低到高）
		 */
		// 方法一：lambda expression
		/*transactions.stream()
					.filter(t -> t.getYear() == 2011)
					.sorted((t1,t2)-> t1.getValue()-t2.getValue())
					.forEach(System.out::println);*/
		// 方法二：利用Comparator.comparing()方法
		/*transactions.stream()
					.filter(t -> t.getYear() == 2011)
					.sorted(Comparator.comparing(t -> t.getValue()))
					.forEach(System.out::println);*/
		// 方法三： 方法引用
		/*transactions.stream()
					.filter(t -> t.getYear() == 2011)
					.sorted(Comparator.comparing(Transaction::getValue))
					.forEach(System.out::println);*/
		/**
		 * (2)  交易员都在哪些不同的城市工作过？
		 */
	/*	transactions.stream()
					.map(t -> t.getTrader().getCity())
					.distinct()
					.forEach(System.out::println);*/
		/**
		 * (3)  查找所有来自于剑桥的交易员，并按姓名排序
		 */
		/*transactions.stream()
					.map(t -> t.getTrader())
					.filter(trader -> trader.getCity().equals("Cambridge"))
					.sorted(Comparator.comparing(Trader::getName))
					.distinct()
					.forEach(System.out::println);*/
		/**
		 * (4)  返回所有交易员的姓名字符串，按字母顺序排序
		 */
		// 方法一
		/*transactions.stream()
					.map(t -> t.getTrader().getName())
					.distinct()
					.sorted((s1,s2) -> s1.compareTo(s2))
					.forEach(System.out::println);*/
		// 方法二
		
		/*transactions.stream()
		.map(t -> t.getTrader().getName())
		.distinct()
		.sorted(String::compareTo)
		.reduce((s1, s2)-> s1+" "+s2)
		.ifPresent(System.out::println);*/
		 
		/**
		 * (5)  有没有交易员是在米兰工作的
		 */
		// 方法一 当查找不存在时，可能会出现nullPointerException
		/*transactions.stream()
					.map(t -> t.getTrader())
					.distinct()
					.filter(trader -> trader.getCity().equals("Milan"))
					.forEach(System.out::println);*/
		// 利用Optional类，可以避免空指针异常
		/*transactions.stream()
					.map(t-> t.getTrader())
					.distinct()
					.filter(trader -> trader.getCity().equals("Milan"))
					.findAny()
					.ifPresent(System.out::println);*/
		// 方法三
		/*boolean milanBased = transactions.stream()
					.anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		System.out.println(milanBased);*/
		/**
		 * (6)  打印生活在剑桥的交易员的所有交易额
		 */
		// 方法一
		/*transactions.stream()
					.filter(t -> t.getTrader().getCity().equals("Cambridge"))
					.map(t -> t.getValue())
					.forEach(System.out::println);*/
		// 方法二
		/*transactions.stream()
		.filter(t -> t.getTrader().getCity().equals("Cambridge"))
		.forEach(t -> System.out.print(t.getValue() + " "));*/
		/**
		 * (7)  所有交易中，最高的交易额是多少？
		 */
		// 方法一
		/*transactions.stream()
		.max(Comparator.comparing(Transaction::getValue))
		.ifPresent(t -> System.out.println(t.getValue()));*/
		// 方法二
		/*transactions.stream()
					.map(Transaction::getValue)
					.max(Comparator.comparing(i->i)) // max((i,j)->i-j)
					.ifPresent(System.out::println);*/
		// 方法三
		transactions.stream()
					.map(Transaction::getValue)
					.reduce(Integer::max)
					.ifPresent(System.out::println);
		/**
		 * (8)  找到交易额最小的交易
		 */
		// 方法一
		/*transactions.stream()
		.min(Comparator.comparing(Transaction::getValue))
		.ifPresent(System.out::println);*/
		// 方法二
		transactions.stream()
					.reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
					.ifPresent(System.out::println);;
	}
}

class Trader {
	private final String name;
	private final String city;
	
	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	
	@Override
	public String toString() {
		return "Trader: " + name + " in " + city;
	}
	
}

class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;
	
	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	
	public Trader getTrader() {
		return trader;
	}
	public int getYear() {
		return year;
	}
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "{" + trader + ", year: " + year + ", value: " + value + "}";
	}
}