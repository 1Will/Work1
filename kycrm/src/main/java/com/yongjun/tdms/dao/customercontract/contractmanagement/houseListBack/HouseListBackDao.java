package com.yongjun.tdms.dao.customercontract.contractmanagement.houseListBack;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack.HouseListBack;

public abstract interface HouseListBackDao {
	public abstract void storeHouseListBack(HouseListBack paramHouseListBack);

	public abstract HouseListBack loadHouseListBack(Long paramLong);

	public abstract List<HouseListBack> loadHouseListBack();

	public abstract List<HouseListBack> loadAllHouseListBack(Long[] paramArrayOfLong);

	public abstract void deleteHouseListBack(HouseListBack paramHouseListBack);

	public abstract void deleteAllHouseListBack(List<HouseListBack> paramList);

	public abstract List<HouseListBack> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<HouseListBack> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
	public abstract double getSum(long id) throws DaoException;
}
