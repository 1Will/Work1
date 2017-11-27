package com.yongjun.tdms.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date start, Date end) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return (int) ((df.parse(df.format(end)).getTime() -df.parse(df.format(start)).getTime()) / (24 * 60 * 60 * 1000)+1);
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String start, String end) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return (int) ((df.parse(end).getTime() -df.parse(start).getTime()) / (24 * 60 * 60 * 1000)+1);
	}
}
