package main.service;

import main.pojo.Weekly;

import org.hibernate.Session;

public interface WeeklyService {
    
     public void saveWeekly(Weekly weekly);//�����ܼƻ�
    
     public Weekly getWeeklyById(Long id);//����id��ȡ�ܱ�
     
     public String getMaxWeeklyCode(String code,Long ratId);//���ݼ�¼�ߺ�codeǰ׺��ȡ��ǰ���code
    
	 public Session getSuperSession();
	
	    
		
		
}
