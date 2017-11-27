package com.yongjun.tdms.service.expensemanagement.electricFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;

public interface ElectricFloorFeeManager {

	public abstract void storeElectricFloorFee(ElectricFloorFee paramElectricFloorFee);

	public abstract ElectricFloorFee loadElectricFloorFee(Long paramLong);

	public abstract List<ElectricFloorFee> loadElectricFloorFee();

	public abstract List<ElectricFloorFee> loadAllElectricFloorFee(Long[] paramArrayOfLong);

	public abstract void deleteElectricFloorFee(ElectricFloorFee paramElectricFloorFee);

	public abstract void deleteAllElectricFloorFee(List<ElectricFloorFee> paramList);

	public abstract List<ElectricFloorFee> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ElectricFloorFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
