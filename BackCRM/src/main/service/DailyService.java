package main.service;

import java.util.List;

import main.pojo.Daily;

import org.hibernate.Session;

public interface DailyService {
    
     public void saveDaily(Daily daily);
     
     public void updateDaily(Daily daily);//�����ձ�
    
     public List<Daily> getAllDaily();
     
     public Daily getDailyById(Long id);
     
     public Long getLastId();//��ȡ���һ�����ݵ�ID
     
     public void updateDaily(String leaderIdea,Long id );//����id����
     
     public List<Daily> getDailyByDate(String date,String name);//�������ں�������Ʒ���Daily����
     
	 public Session getSuperSession();
	
}
