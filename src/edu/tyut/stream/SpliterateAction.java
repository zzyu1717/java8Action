package edu.tyut.stream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @title SpliterateAction.java
 * @description 实现自己的Spliterator
 * @time 2017年5月15日下午7:42:20
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class SpliterateAction {
	public static void main(String[] args) {
		
		final String str = " Nel mezzo del cammin di nostra vita " +
							"mi ritrovai in una selva oscura" +
							" ché la dritta via era smarrita ";
		System.out.println("Found " + countWordsInteratively(str)+" word");
		
		// 不能并行处理
		Stream<Character> stream = IntStream.rangeClosed(0,str.length()-1).mapToObj(str::charAt);
		int result = WordCounter.countWords(stream);
		System.out.println("Found " + result+" word");

		// 并行处理
		Spliterator<Character> spliterator = new WordCounterSpliterator(str);
		Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
		System.out.println("Found " + WordCounter.countWords(parallelStream) +" word");
		
	}
	/**
	 * 一个迭代式字数统计方法
	 * @param s
	 * @return
	 */
	public static  int countWordsInteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c :s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) 
					counter ++;
				lastSpace = false;
			}
		}
		return counter;
	}
}

/**
 * 用来遍历Character流时计数的类
 * @author ZZY
 *
 */
class WordCounter {
	private final int counter;
	private final boolean lastSpace;
	
	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	public WordCounter accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {
			return lastSpace ? new WordCounter(counter+1, false) : this;
		}
	}
	
	public WordCounter combiner(WordCounter wordCounter) {
		return new WordCounter(counter+wordCounter.counter, wordCounter.lastSpace);
	}
	
	public int getCounter() {
		return counter;
	}
	
	public static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0,true), WordCounter::accumulate, WordCounter::combiner);
		return wordCounter.getCounter();
	}
}

/**
 * 为Character实现一个Spliterator，确保String不是在随机位置拆开的，而只能在词尾拆开
 * @author ZZY
 *
 */
class WordCounterSpliterator implements Spliterator<Character> {
	private final String string;
	private int currentChar = 0;
	
	public WordCounterSpliterator(String string) {
		this.string = string;
	}
	
	// 处理当前字符
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(string.charAt(currentChar ++));
		return currentChar < string.length(); // 还有字符要处理，返回true
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length();
		if (currentSize < 10) {
			return null; // 返回null，表示要拆分的string已经足够小，可以顺序处理
		}
		// 将试探拆分位置设定为要解析的String的中间
		for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
			// 让拆分位置前进直到下一个空格
			if (Character.isWhitespace(string.charAt(splitPos))) {
				// 创建一个新的WordCounterSpliterator来解析string从开始到拆分位置的部分
				Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
				// 将这个WordCounterSpliterator的起始位置设为拆分位置
				currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
	
}
























