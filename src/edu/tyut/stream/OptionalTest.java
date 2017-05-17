package edu.tyut.stream;

import java.util.Optional;
import java.util.Properties;

/**
 * @title OptionalTest.java
 * @description Optional类相关使用方法
 * @time 2017年5月16日下午3:52:32
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class OptionalTest {
	public static void main(String[] args) {
		/**
		 * 创建Optional对象
		 */
//		Car car = new Car();
		// 1、声明一个空的Optional
//		Optional<Car> optCar = Optional.empty();
		// 2、依据一个非空值创建Optional
//		Optional<Car> optCar1 = Optional.of(car);
		// 3、可接受null的Optional
//		Optional<Car> optCar2 = Optional.ofNullable(car);
		
		Insurance insurance = new Insurance();
		// map() 方法
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
		Optional<String> name = optInsurance.map(Insurance::getName);
		
		Properties props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");
		System.out.println(readDuration(props, "a"));
		System.out.println(readDuration(props, "b"));
	}
	
	public static int readDuration(Properties props, String name) {
		return Optional.ofNullable(props.getProperty(name))
				.flatMap(OptionalUtility::stringToInt)
				.filter(i -> i > 0).orElse(0);
	}
	
	// 两个Optional对象组合
	public static  Optional<Insurance> nullSafeFindCheapestInsurance (Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}
	private static Insurance findCheapestInsurance(Person person, Car car) {
		return car.getInsurance().get();
	}
	public static String getCarInsuranceName(Optional<Person> person) {
		return person.flatMap(Person::getCar)
					.flatMap(Car::getInsurance)
					.map(Insurance::getName)
					.orElse("Unkonwn");
	}
}


class Person { // 人可能有车，也可能没车，因此将这个字段声明为Optional
	private Optional<Car> car;
	public Optional<Car> getCar() {
		return car;
	}
}

class Car {
	// 在域模型中使用Optional ，无法实现序列化
	private Optional<Insurance> insurance;
	public Optional<Insurance> getInsurance() {
		return insurance;
	}
}

class Insurance { // 保险公司必须有名字
	private String name;
	public String getName() {
		return name;
	}
}

/**
 * Optional 工具类
 * @author ZZY
 *
 */
class OptionalUtility {
	public static Optional<Integer> stringToInt(String str) {
		try {
			return Optional.ofNullable(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}


