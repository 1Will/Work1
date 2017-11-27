package com.yongjun.tdms.service.expensemanagement.waterFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;

public interface WaterFloorFeeManager {

	public abstract void storeWaterFloorFee(WaterFloorFee paramWaterFloorFee);

	public abstract WaterFloorFee loadWaterFloorFee(Long paramLong);

	public abstract List<WaterFloorFee> loadWaterFloorFee();

	public abstract List<WaterFloorFee> loadAllWaterFloorFee(Long[] paramArrayOfLong);

	public abstract void deleteWaterFloorFee(WaterFloorFee paramWaterFloorFee);

	public abstract void deleteAllWaterFloorFee(List<WaterFloorFee> paramList);

	public abstract List<WaterFloorFee> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<WaterFloorFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
    
}
