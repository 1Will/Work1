package com.yongjun.tdms.dao.CustomerRelationship.technology;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;

public abstract interface TechnologyDao {
	  public abstract void storeTechnology(Technology technology);

	  public abstract Technology loadTechnology(Long paramLong);

	  public abstract List<Technology> loadTechnology();

	  public abstract List<Technology> loadAllTechnology(Long[] paramArrayOfLong);

	  public abstract void deleteTechnology(Technology paramTechnology);

	  public abstract void deleteAllTechnology(List<Technology> paramList);

	  public abstract List<Technology> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<Technology> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
