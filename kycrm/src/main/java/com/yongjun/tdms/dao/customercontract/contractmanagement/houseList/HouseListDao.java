package com.yongjun.tdms.dao.customercontract.contractmanagement.houseList;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;

public abstract interface HouseListDao {
	public abstract void storeHouseList(HouseList paramHouseList);

	public abstract HouseList loadHouseList(Long paramLong);

	public abstract List<HouseList> loadHouseList();

	public abstract List<HouseList> loadAllHouseList(Long[] paramArrayOfLong);

	public abstract void deleteHouseList(HouseList paramHouseList);

	public abstract void deleteAllHouseList(List<HouseList> paramList);

	public abstract List<HouseList> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<HouseList> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;

	public abstract double getSum(long paramLong) throws DaoException;
	
	public double getAllSquare(long id) throws DaoException;

	public abstract String getHouseListEndTime(long id);
}
