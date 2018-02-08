package main.dao;


import java.util.List;

import org.hibernate.Session;

import main.pojo.Weekly;

public interface WeeklyDao {
	
	public void saveWeekly(Weekly weekly);//保存周计划
    
    public Weekly getWeeklyById(Long id);//根据id获取周报
    
    public void updateWeekly(Weekly weekly);//更新日报

//    public List<Weekly> getWeeklyByweeklyName(String weeklyName,String thisYear, String loginName);//根据weeklyName获取周报
   
    public List<Weekly> getWeeklyByweeklyName(String weeklyName, String loginName);//根据weeklyName获取周报

    public List<Weekly> getWeeklyByweekId(Long weekId, String loginName);//根据weekId loginName 获取周报
    
    public String getMaxWeeklyCode(String code,Long ratId);//根据记录者和code前缀获取当前最大code
	
	public Session getSuperSession();
	
	
}
