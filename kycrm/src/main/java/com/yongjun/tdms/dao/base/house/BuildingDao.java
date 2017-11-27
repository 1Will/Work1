package com.yongjun.tdms.dao.base.house;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.Building;

public abstract interface BuildingDao
{
  public abstract void storeBuilding(Building paramBuilding);

  public abstract Building loadBuilding(Long paramLong);

  public abstract List<Building> loadAllBuilding(Long[] paramArrayOfLong);

  public abstract List<Building> loadAllBuilding();

  public abstract void deleteBuilding(Building paramBuilding);

  public abstract void deleteAllBuilding(Collection<Building> paramCollection);

  public abstract List<Building> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public abstract List<Building> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
		    throws DaoException;
}

