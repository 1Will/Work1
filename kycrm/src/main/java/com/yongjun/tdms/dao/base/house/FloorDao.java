package com.yongjun.tdms.dao.base.house;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.Floor;

public abstract interface FloorDao
{
  public abstract void storeFloor(Floor paramFloor);

  public abstract Floor loadFloor(Long paramLong);

  public abstract List<Floor> loadAllFloor(Long[] paramArrayOfLong);

  public abstract List<Floor> loadAllFloor();

  public abstract void deleteFloor(Floor paramFloor);

  public abstract void deleteAllFloor(Collection<Floor> paramCollection);

  public abstract List<Floor> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public abstract List<Floor> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
		    throws DaoException;
}

