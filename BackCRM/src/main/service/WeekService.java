package main.service;

import java.util.List;

import main.pojo.Week;

import org.hibernate.Session;

public interface WeekService {
      
	   public Week getWeekById(Long weekId); // ����weekId��ȡ ʵ��
	   
	    public List<Week> getWeekByMonth(String month);  //�����·� ģ����ѯ��ȡ  ������
	    
		public List<Week> getWeekByMonthAndYear(String month,int year);  //�����·� �ͱ��� ����2017�� ģ����ѯ��ȡ  ������
	    
		public Session getSuperSession();
	
}
