package com.yongjun.tdms.service.base.house;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.House;

public abstract interface HouseManager
{
  public abstract void storeHouse(House paramHouse);

  public abstract List<House> loadAllHouse(Long[] paramArrayOfLong);

  public abstract void deleteAllHouse(List<House> paramList);

  public abstract House loadHouse(Long paramLong);

  public abstract void deleteHouse(House paramHouse);

  public abstract List<House> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public abstract List<House> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
		    throws DaoException;

  public abstract List<House> loadAllHouse();
  
  public abstract void saveAllHouse(List<House> paramList);
}

