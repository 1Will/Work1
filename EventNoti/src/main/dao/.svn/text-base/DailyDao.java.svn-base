package main.dao;


import java.util.List;

import org.hibernate.Session;

import main.pojo.BackVisit;
import main.pojo.Daily;

public interface DailyDao {
	
	public void saveDaily(Daily daily);//保存日报
    
    public List<Daily> getAllDaily(); //初始化获取所有日报集合
    
    public Daily getDailyById(Long id); //根据id获取日报
    
    public Long getLastId();//获取最后一条数据的ID
    
    public void updateDaily(String leaderIdea,Long id );//根据id更新
    
    public List<Daily> getDailyByDate(String date,String name);//根据日期和姓名简称返回Daily集合
	
	public Session getSuperSession();
	
	
}
