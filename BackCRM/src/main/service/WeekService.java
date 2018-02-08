package main.service;

import java.util.List;

import main.pojo.Week;

import org.hibernate.Session;

public interface WeekService {
      
	   public Week getWeekById(Long weekId); // 根据weekId获取 实体
	   
	    public List<Week> getWeekByMonth(String month);  //根据月份 模糊查询获取  周名称
	    
		public List<Week> getWeekByMonthAndYear(String month,int year);  //根据月份 和本年 ‘如2017’ 模糊查询获取  周名称
	    
		public Session getSuperSession();
	
}
