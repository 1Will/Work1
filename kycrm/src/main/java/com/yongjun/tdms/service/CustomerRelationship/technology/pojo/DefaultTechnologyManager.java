package com.yongjun.tdms.service.CustomerRelationship.technology.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.technology.TechnologyDao;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;
import com.yongjun.tdms.service.CustomerRelationship.technology.TechnologyManager;

public class DefaultTechnologyManager extends BaseManager implements TechnologyManager{
    private TechnologyDao  technologyDao;
	public DefaultTechnologyManager(TechnologyDao technologyDao) {
		this.technologyDao = technologyDao;
	}

	public void storeTechnology(Technology technology) {
		technologyDao.storeTechnology(technology);
	} 

	public Technology loadTechnology(Long paramLong) {
		return technologyDao.loadTechnology(paramLong);
	}

	public List<Technology> loadTechnology() {
		return technologyDao.loadTechnology();
	}

	public List<Technology> loadAllTechnology(Long[] paramArrayOfLong) {
		return technologyDao.loadAllTechnology(paramArrayOfLong);
	}

	public void deleteTechnology(Technology paramTechnology) {
		technologyDao.deleteTechnology(paramTechnology);		
	}

	public void deleteAllTechnology(List<Technology> paramList) {
		technologyDao.deleteAllTechnology(paramList);		
	}

	public List<Technology> loadByKey(String paramString, Object paramObject) throws DaoException {
		return technologyDao.loadByKey(paramString, paramObject);
	}

	public List<Technology> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return technologyDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
