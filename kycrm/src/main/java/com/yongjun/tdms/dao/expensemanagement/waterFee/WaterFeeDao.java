package com.yongjun.tdms.dao.expensemanagement.waterFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;

public interface WaterFeeDao {
	 public abstract void storeWaterFee(WaterFee paramWaterFee);

	  public abstract WaterFee loadWaterFee(Long paramLong);

	  public abstract List<WaterFee> loadWaterFee();

	  public abstract List<WaterFee> loadAllWaterFee(Long[] paramArrayOfLong);

	  public abstract void deleteWaterFee(WaterFee paramWaterFee);

	  public abstract void deleteAllWaterFee(List<WaterFee> paramList);

	  public abstract List<WaterFee> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<WaterFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

	public abstract WaterFee loadByStartTime(Date date);
}
