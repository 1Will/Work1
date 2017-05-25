package main.dao;

import java.util.List;

import main.pojo.BackVisit;

import org.hibernate.Session;


public interface BackVisitDao {

	public void saveBackVisit(BackVisit backVisit);

	public void updateBackVisit(BackVisit backVisit);//更新实体
	
	public List<BackVisit> getBackVisitByDate(String date,String name);//根据日期和姓名返回backVisit集合
	
	public List<BackVisit> getAllBackVisit(Long[] backVisitIds);//根据ids返回backlist集合

	public List<BackVisit> getBackVisitByCustomerName(String customerName);//根据customerName返回backlist集合

	public BackVisit getBackVisitById(Long id);//根据id返回backlist
	
	public Session getSuperSession();

}
