package edu.tyut.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @title ObserverPattern.java
 * @description TODO
 * @time 2017年5月16日上午10:06:00
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */

// 观察者接口
interface Observer {
	void notify(String tweet);
}

// 定义不同的观察者
class NYTimes implements Observer {

	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}
	
}
class Guardian implements Observer {
	
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in Landon... " + tweet);
		}
	}
	
}
class LeMonde implements Observer {
	
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("wine")) {
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
	
}

// 定义主题
interface Subject {
	void registerObserver(Observer o);  
	void notifyObservers(String tweet);
}

class Feed implements Subject {
	
	private final List<Observer> observers = new ArrayList<>();
	
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
	
}


public class ObserverPattern {
	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new LeMonde());
		
		// 使用lambda表达式
		f.registerObserver(tweet -> {
			if (tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in Landon... " + tweet);
			}
		});
		f.notifyObservers("The queen said her favourite book is Java8 in Action");
	}
}
