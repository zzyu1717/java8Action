package edu.tyut.stream;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @title TimeTest.java
 * @description Time相关的操作
 * @time 2017年5月17日下午4:35:16
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class TimeTest {
	public static void main(String[] args) {
		/**
		 * LocalDate 
		 */
//		LocalDate date = LocalDate.of(2014, 3, 18);
//		int year = date.getYear();
//		Month month = date.getMonth();
//		int day = date.getDayOfMonth();
//		DayOfWeek dow = date.getDayOfWeek();
//		int len = date.lengthOfMonth();
//		boolean leap = date.isLeapYear();
//		LocalDate today = LocalDate.now();
//		System.out.println(today);
//		int year = date.get(ChronoField.YEAR);
//		System.out.println(year);
		LocalDate date = LocalDate.parse("2019-12-15");
//		System.out.println(date);
		LocalTime time = LocalTime.of(14, 0, 30);
//		System.out.println(time);
		/**
		 * LocalDateTime
		 */
//		LocalDateTime dt1 = LocalDateTime.of(2014, 5, 15, 17, 10);
//		LocalDateTime dt2 = date.atTime(15, 20);
//		LocalDateTime dt3 = time.atDate(date);
//		LocalDateTime dt4 = LocalDateTime.of(date, time);
//		System.out.println(dt1);
//		System.out.println(dt2);
//		System.out.println(dt3);
//		System.out.println(dt4);
//		
//		LocalDate date1 = dt4.toLocalDate();
//		LocalTime time1 = dt4.toLocalTime();
		/**
		 * Instant 机器的日期和时间格式，以Unix元年时间开始所经历的秒数进行计算
		 */
//		Instant instant = Instant.ofEpochMilli(3);
//		System.out.println(instant);
//		System.out.println(Instant.ofEpochSecond(2, 999_999_999));
//		System.out.println(Instant.now());
//		System.out.println(System.currentTimeMillis());
		
		/**
		 * ZoneId 时区
		 */
		ZoneId zone = ZoneId.of("Asia/Shanghai");
		ZonedDateTime zdt = date.atStartOfDay(zone);
		System.out.println(zdt);
		
	}
}
