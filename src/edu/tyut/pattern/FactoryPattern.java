package edu.tyut.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @title FactoryPattern.java
 * @description TODO
 * @time 2017年5月16日下午12:25:40
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class FactoryPattern {
	final static Map<String, Supplier<Product>> map = new HashMap<>();
	
	static {
		map.put("loan", Loan::new);
		map.put("stock", Stock::new);
		map.put("bond", Bond::new);
	}
	
	public static void main(String[] args) {
		Product p = ProductFactory.createProduct("loan");
		
		// 使用lambda表达式
		Product p1 = FactoryPattern.createProduct("bond");
	}
	
	public static Product createProduct(String name) {
		Supplier<Product> p = map.get(name);
		if (p != null) 
			return p.get();
		throw new IllegalArgumentException("No such product : " + name);
	}
}

class ProductFactory {
	public static Product createProduct(String name) {
		switch (name) {
		case "loan" : return new Loan();
		case "stock" : return new Stock();
		case "bond" : return new Bond();
		default : throw new RuntimeException("No such product: " + name);
		}
	}
}

interface Product {}
class Loan implements Product {}
class Stock implements Product {}
class Bond implements Product {}
