package edu.tyut.pattern;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @title ResponsibeChainPattern.java
 * @description 责任链模式
 * @time 2017年5月16日上午10:53:40
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */

abstract class ProcessingObject<T> {
	protected ProcessingObject<T> successor;
	
	public void setSuccessor(ProcessingObject<T> successor) {
		this.successor = successor;
	}
	
	public T handle(T input) {
		T r = handleWork(input);
		if (successor != null) {
			return successor.handle(r);
		}
		return r;
	}
	
	abstract protected T handleWork(T input);
}

class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {
		return "From Raoul, Mario and Alan : " + input;
	}
	
}
class SpellCheckerProcessing extends ProcessingObject<String> {
	
	@Override
	protected String handleWork(String input) {
		return input.replaceAll("labda", "lambda");
	}
	
}

public class ResponsibeChainPattern {
	public static void main(String[] args) {
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();
		
		p1.setSuccessor(p2);
		
		String result = p1.handle("Aren't labdas really sexy?");
		
		System.out.println(result);
		
		// 使用Lambda表达式
		UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan : " + text;
		UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda","labmda");
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		System.out.println(pipeline.apply("Aren't labdas really sexy?!!!"));
	}
}
