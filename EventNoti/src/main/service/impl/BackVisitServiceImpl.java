package main.service.impl;

import main.dao.BackVisitDao;
import main.pojo.BackVisit;
import main.service.BackVisitService;

import org.hibernate.Session;

public class BackVisitServiceImpl implements BackVisitService {
	
	private BackVisitDao backVisitDao;

	public BackVisitDao getBackVisitDao() {
		return backVisitDao;
	}

	public void setBackVisitDao(BackVisitDao backVisitDao) {
		this.backVisitDao = backVisitDao;
	}

	public void saveBackVisit(BackVisit backVisit) {
		backVisitDao.saveBackVisit(backVisit);
		
	}

	

	
	public Session getSuperSession() {
		return backVisitDao.getSuperSession();
	}

}
