package com.yongjun.tdms.service.workReport.apply;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.apply.ApplyCar;

public abstract interface ApplyCarManager
{
	  public abstract void storeApplyCar(ApplyCar applyCar);

	  public abstract void deleteApplyCar(ApplyCar applyCar);

	  public abstract void deleteApplyCar(Collection<ApplyCar> paramCollection);

	  public abstract List<ApplyCar> loadAllApplyCar(Long[] paramArrayOfLong);

	  public abstract ApplyCar loadApplyCar(Long paramLong);

	  public abstract List<ApplyCar> loadAllApplyCar();

	  public abstract List<ApplyCar> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ApplyCar> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

	public abstract void disabledAllApplyCars(List<ApplyCar> cars);

	public abstract void enabledAllApplyCars(List<ApplyCar> cars);
	}

