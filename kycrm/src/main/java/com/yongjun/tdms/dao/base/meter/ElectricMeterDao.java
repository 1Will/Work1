package com.yongjun.tdms.dao.base.meter;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public abstract interface ElectricMeterDao
{
  public abstract void storeElectricMeter(ElectricMeter paramElectricMeter);

  public abstract ElectricMeter loadElectricMeter(Long paramLong);

  public abstract List<ElectricMeter> loadAllElectricMeter(Long[] paramArrayOfLong);

  public abstract List<ElectricMeter> loadAllElectricMeter();

  public abstract void deleteElectricMeter(ElectricMeter paramElectricMeter);

  public abstract void deleteAllElectricMeter(Collection<ElectricMeter> paramCollection);

  public abstract List<ElectricMeter> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public abstract List<ElectricMeter> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
		    throws DaoException;
}

