package main.service.impl;

import java.util.Date;
import java.util.List;

import main.dao.BackVisitDao;
import main.pojo.BackVisit;
import main.service.BackVisitService;

import org.hibernate.Session;

public class BackVisitServiceImpl implements BackVisitService {
	
	private BackVisitDao backVisitDao;


	public void saveBackVisit(BackVisit backVisit) {
		backVisitDao.saveBackVisit(backVisit);
		
	}

	public void updateBackVisit(BackVisit backVisit) {
		backVisitDao.updateBackVisit(backVisit);
		
	}

	@Override
	public List<BackVisit> getBackVisitByDate(String date, String name) {
		return backVisitDao.getBackVisitByDate(date,name );
	}
	
	@Override
	public List<BackVisit> getBackVisitByCustomerName(String customerName) {
		return backVisitDao.getBackVisitByCustomerName(customerName);
	}
	
	@Override
	public List<BackVisit> getAllBackVisit(Long[] backVisitIds) {
		return backVisitDao.getAllBackVisit(backVisitIds);
	}

	@Override
	public BackVisit getBackVisitById(Long id) {
		return backVisitDao.getBackVisitById(id);
	}
	
	public Session getSuperSession() {
		return backVisitDao.getSuperSession();
	}

	
	public BackVisitDao getBackVisitDao() {
		return backVisitDao;
	}
	
	public void setBackVisitDao(BackVisitDao backVisitDao) {
		this.backVisitDao = backVisitDao;
	}



}
