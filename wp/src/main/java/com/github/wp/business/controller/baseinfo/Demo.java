package com.github.wp.business.controller.baseinfo;

import java.util.Date;

import com.github.wp.system.util.common.DateUtil;

public class Demo {

	public static void main(String[] args) {
		String currentDate = DateUtil.formatDateToStr(new Date(),"yyyy-MM-dd HH:mm:ss.SSS");
		String aa = currentDate.substring(2, 4)+currentDate.substring(5, 7)+currentDate.substring(8, 10)+currentDate.substring(20);
		System.out.println("-----3--3-3-----"+aa);
	}
}
