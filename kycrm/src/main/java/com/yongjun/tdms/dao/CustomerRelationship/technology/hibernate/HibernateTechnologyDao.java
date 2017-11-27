package com.yongjun.tdms.dao.CustomerRelationship.technology.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.technology.TechnologyDao;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;

public class HibernateTechnologyDao extends BaseHibernateDao implements TechnologyDao{

	public void storeTechnology(Technology technology) {
         store(technology);		
	}

	public Technology loadTechnology(Long paramLong) {
		return (Technology) load(Technology.class, paramLong);
	}

	public List<Technology> loadTechnology() {
		return  loadAll(Technology.class);
	}

	public List<Technology> loadAllTechnology(Long[] paramArrayOfLong) {
		return loadAll(Technology.class, paramArrayOfLong);
	}

	public void deleteTechnology(Technology paramTechnology) {
		delete(paramTechnology);		
	}

	public void deleteAllTechnology(List<Technology> paramList) {
		deleteAll(paramList);
	}

	public List<Technology> loadByKey(String paramString, Object paramObject) throws DaoException {
		return loadByKey(Technology.class, paramString, paramObject);
	}

	public List<Technology> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return loadByKeyArray(Technology.class, paramArrayOfString, paramArrayOfObject);	}

}
