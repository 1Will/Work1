package main.service;

import java.util.List;

import main.pojo.Daily;

import org.hibernate.Session;

public interface DailyService {
    
     public void saveDaily(Daily daily);
     
     public void updateDaily(Daily daily);//更新日报
    
     public List<Daily> getAllDaily();
     
     public Daily getDailyById(Long id);
     
     public Long getLastId();//获取最后一条数据的ID
     
     public void updateDaily(String leaderIdea,Long id );//根据id更新
     
     public List<Daily> getDailyByDate(String date,String name);//根据日期和姓名简称返回Daily集合
     
	 public Session getSuperSession();
	
}
