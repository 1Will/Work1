package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.AreaDao;
import main.pojo.Area;
import main.service.AreaService;

public class AreaServiceImpl implements AreaService{
    private AreaDao areaDao;
	@Override
	public List<Area> getAllCountryByType() {
		return areaDao.getAllCountryByType();
	}

	@Override
	public List<Area> getAreaByAreaid(Long areaId) {
		return areaDao.getAreaByAreaid(areaId);
	}

	@Override
	public Session getSuperSession() {
		return areaDao.getSuperSession();
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	
}
