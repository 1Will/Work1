package main.dao;


import org.hibernate.Session;

import main.pojo.Weekly;

public interface WeeklyDao {
	
	public void saveWeekly(Weekly weekly);//�����ܼƻ�
    
    public Weekly getWeeklyById(Long id);//����id��ȡ�ܱ�
    
    public String getMaxWeeklyCode(String code,Long ratId);//���ݼ�¼�ߺ�codeǰ׺��ȡ��ǰ���code
	
	public Session getSuperSession();
	
	
}
