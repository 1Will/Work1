package main.dao;

import java.util.List;

import org.hibernate.Session;

import main.pojo.Week;

public interface WeekDao {
    
	public Week getWeekById(Long weekId); // ����weekId��ȡ ʵ��
	
	public List<Week> getWeekByMonth(String month);  //�����·� ģ����ѯ��ȡ  ������

	public List<Week> getWeekByMonthAndYear(String month,int year);  //�����·� �ͱ��� ����2017�� ģ����ѯ��ȡ  ������
    
	public Session getSuperSession();
	
	
}
