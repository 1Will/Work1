package com.yongjun.tdms.service.base.industoryType;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.industryType.Industry;

public interface IndustryManager {
	public abstract void storeIndustry(Industry industry);

	  public abstract Industry loadIndustry(Long id);

	  public abstract List<Industry> loadAllIndustry(Long[] ids);

	  public abstract List<Industry> loadAllIndustry();

	  public abstract void deleteIndustry(Industry industry);

	  public abstract void deleteAllIndustry(Collection<Industry> inudstryCollection);

	  public abstract List<Industry> loadByKey(String key, Object value)
	    throws DaoException;
	  public abstract List<Industry> loadByPid(Long value) throws DaoException;
	  public abstract List<Industry> loadByKeyArray(String[] keys, Object[] values)
			    throws DaoException;
}
