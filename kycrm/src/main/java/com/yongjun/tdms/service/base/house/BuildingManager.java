package com.yongjun.tdms.service.base.house;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.Building;

public abstract interface BuildingManager {
	public abstract void storeBuilding(Building paramBuilding);

	public abstract List<Building> loadAllBuilding(Long[] paramArrayOfLong);

	public abstract void deleteAllBuilding(List<Building> paramList);
	
	public List<Building> loadAllBuilding();

	public abstract Building loadBuilding(Long paramLong);

	public abstract void deleteBuilding(Building paramBuilding);

	public abstract List<Building> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<Building> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
