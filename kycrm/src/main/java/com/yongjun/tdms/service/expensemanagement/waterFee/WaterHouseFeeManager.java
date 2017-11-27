package com.yongjun.tdms.service.expensemanagement.waterFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;

public interface WaterHouseFeeManager {

	public abstract void storeWaterHouseFee(WaterHouseFee paramWaterHouseFee);

	public abstract WaterHouseFee loadWaterHouseFee(Long paramLong);

	public abstract List<WaterHouseFee> loadWaterHouseFee();

	public abstract List<WaterHouseFee> loadAllWaterHouseFee(Long[] paramArrayOfLong);

	public abstract void deleteWaterHouseFee(WaterHouseFee paramWaterHouseFee);

	public abstract void deleteAllWaterHouseFee(List<WaterHouseFee> paramList);

	public abstract List<WaterHouseFee> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<WaterHouseFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
   public abstract Double loadSumFeeByCusId(Long id, Date date);
}
