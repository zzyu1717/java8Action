package edu.tyut.generic;
/**
 * @title GenericTest.java
 * @description 
 * <li> <? extends T>: 是指“上界通配符(Upper Bounds Wildcards)"，不能往里存，可以往外取</li>
 * <li> <? super T> : 是指“下界通配符(Lower Bounds Wildcards)" ，可以往里存，也可以往外取</li>
 * <li>PECS(Producer Extends, Consumer Super)原则：</li>
 * <li> 如果你需要一个列表提供T类型的元素（即你想从列表中读取T类型的元素），
 * 		你需要把这个列表声明成<? extends T>，比如List<? extends Integer>，
 * 		因此你不能往该列表中添加任何元素。频繁往外读取内容的，适用上界Extends      </li>
 * <li> 如果需要一个列表使用T类型的元素（即你想把T类型的元素加入到列表中），
 * 		你需要把这个列表声明成<? super T>，比如List<? super Integer>，
 * 		因此你不能保证从中读取到的元素的类型。经常插入的，适用下界Super		</li>
 * <li> 如果一个列表即要生产，又要消费，你不能使用泛型通配符声明列表，比如List<Integer></li>
 * @see java.util.Collections.copy方法	
 * @time 2017年5月11日下午5:13:40
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class GenericTest {
	public static void main(String...args) {
		// 容器之间不具有和容器内容之间的关系
		//Plate<Fruit> p = new Plate<Apple>(new Apple());
		// 使用上界通配符
//		Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
//		System.out.println(p.get().getName());
		
		/**
		 * 上界通配符的副作用:<? extends Fruit> 会使set()方法失效，但get()方法还有效
		 */
//		p.set(new Apple());
//		p.set(new Fruit());
//		p.set(null);
//		System.out.println(p.get());
//		Fruit f = p.get();
//		System.out.println(f.getName());
//		Apple a = p.get(); //error
		/**
		 * set()有效，get()必须返回Object类型
		 */
		Plate<? super Fruit> p = new Plate<>(new Fruit());
		p.set(new Fruit());
		Object obj = p.get();
		System.out.println(obj);
		
	}
	 
}

class Fruit {
	private String name = "fruit";
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
class Apple extends Fruit {
	private String name = "apple";
	public String getName() {
		return name;
	}
}

// 简单容器类
class Plate<T> {
	private T item;
	public Plate(T t) {
		item = t;
	}
	public void set(T t) {
		item = t;
	}
	public T get() {
		return item;
	}
}