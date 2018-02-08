package main.service;

import java.util.List;

import main.pojo.BackVisit;

import org.hibernate.Session;

public interface BackVisitService {

	public void saveBackVisit(BackVisit backVisit);
	
	public void updateBackVisit(BackVisit backVisit);//����ʵ��

	public List<BackVisit> getBackVisitByDate(String date,String name);//�������ں���������backVisit����
	
	public List<BackVisit> getAllBackVisit(Long[] backVisitIds);//����ids����backlist����
	
	public List<BackVisit> getBackVisitByCustomerName(String customerName);//����customerName ���ط����ڵ������з���backlist����
	
	public BackVisit getBackVisitById(Long id);//����id����backlist

	public Session getSuperSession();
	
	

}
