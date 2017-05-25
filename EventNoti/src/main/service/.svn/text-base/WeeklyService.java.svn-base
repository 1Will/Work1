package main.service;

import main.pojo.Weekly;

import org.hibernate.Session;

public interface WeeklyService {
    
     public void saveWeekly(Weekly weekly);//保存周计划
    
     public Weekly getWeeklyById(Long id);//根据id获取周报
     
     public String getMaxWeeklyCode(String code,Long ratId);//根据记录者和code前缀获取当前最大code
    
	 public Session getSuperSession();
	
	    
		
		
}
