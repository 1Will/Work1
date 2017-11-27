package com.yongjun.tdms.service.base.meter;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public abstract interface ElectricMeterManager {
	public abstract void storeElectricMeter(ElectricMeter paramElectricMeter);

	public abstract List<ElectricMeter> loadAllElectricMeter(Long[] paramArrayOfLong);

	public abstract void deleteAllElectricMeter(List<ElectricMeter> paramList);
	
	public List<ElectricMeter> loadAllElectricMeter();

	public abstract ElectricMeter loadElectricMeter(Long paramLong);

	public abstract void deleteElectricMeter(ElectricMeter paramElectricMeter);

	public abstract List<ElectricMeter> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ElectricMeter> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
