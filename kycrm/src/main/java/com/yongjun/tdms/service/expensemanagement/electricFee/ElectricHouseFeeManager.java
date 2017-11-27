package com.yongjun.tdms.service.expensemanagement.electricFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;

public interface ElectricHouseFeeManager {

	public abstract void storeElectricHouseFee(ElectricHouseFee paramElectricHouseFee);

	public abstract ElectricHouseFee loadElectricHouseFee(Long paramLong);

	public abstract List<ElectricHouseFee> loadElectricHouseFee();

	public abstract List<ElectricHouseFee> loadAllElectricHouseFee(Long[] paramArrayOfLong);

	public abstract void deleteElectricHouseFee(ElectricHouseFee paramElectricHouseFee);

	public abstract void deleteAllElectricHouseFee(List<ElectricHouseFee> paramList);

	public abstract List<ElectricHouseFee> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ElectricHouseFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
	public abstract Double loadSumFeeByCusId(Long id, Date date);

}
