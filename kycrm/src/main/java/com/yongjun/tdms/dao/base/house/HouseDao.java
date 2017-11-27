package com.yongjun.tdms.dao.base.house;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.House;

public abstract interface HouseDao
{
  public abstract void storeHouse(House paramHouse);

  public abstract House loadHouse(Long paramLong);

  public abstract List<House> loadAllHouse(Long[] paramArrayOfLong);

  public abstract List<House> loadAllHouse();

  public abstract void deleteHouse(House paramHouse);

  public abstract void deleteAllHouse(Collection<House> paramCollection);

  public abstract List<House> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public abstract List<House> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
		    throws DaoException;
}

