package main.service;

import java.util.List;

import main.pojo.Weekly;

import org.hibernate.Session;

public interface WeeklyService {
    
     public void saveWeekly(Weekly weekly);//�����ܼƻ�
     
     public void updateWeekly(Weekly weekly);//�����ձ�
    
     public Weekly getWeeklyById(Long id);//����id��ȡ�ܱ�
     
     public List<Weekly> getWeeklyByweeklyName(String weeklyNameString,String loginName);//����weeklyName��ȡ�ܱ�
     
   //  public List<Weekly> getWeeklyByweeklyName(String weeklyNameString,String thisYear,String loginName);//����weeklyName��ȡ�ܱ�
    
     public List<Weekly> getWeeklyByweekId(Long weekId, String loginName);//����weekId loginName ��ȡ�ܱ�
     
     public String getMaxWeeklyCode(String code,Long ratId);//���ݼ�¼�ߺ�codeǰ׺��ȡ��ǰ���code
    
	 public Session getSuperSession();
	
	    
		
		
}
