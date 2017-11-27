package com.yongjun.tdms.dao.CustomerRelationship.newStandard;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.newStandard.NewStandard;

public abstract interface NewStandardDao {
	 public abstract void storeNewStandard(NewStandard newStandard);

	  public abstract NewStandard loadNewStandard(Long paramLong);

	  public abstract List<NewStandard> loadNewStandard();

	  public abstract List<NewStandard> loadAllNewStandard(Long[] paramArrayOfLong);

	  public abstract void deleteNewStandard(NewStandard paramNewStandard);

	  public abstract void deleteAllNewStandard(List<NewStandard> paramList);

	  public abstract List<NewStandard> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<NewStandard> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

}
