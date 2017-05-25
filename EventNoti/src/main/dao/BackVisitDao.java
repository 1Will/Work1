package main.dao;

import java.util.List;

import main.pojo.BackVisit;

import org.hibernate.Session;


public interface BackVisitDao {

	public void saveBackVisit(BackVisit backVisit);

	public void updateBackVisit(BackVisit backVisit);//����ʵ��
	
	public List<BackVisit> getBackVisitByDate(String date,String name);//�������ں���������backVisit����
	
	public List<BackVisit> getAllBackVisit(Long[] backVisitIds);//����ids����backlist����

	public List<BackVisit> getBackVisitByCustomerName(String customerName);//����customerName����backlist����

	public BackVisit getBackVisitById(Long id);//����id����backlist
	
	public Session getSuperSession();

}
