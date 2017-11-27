package com.yongjun.tdms.service.base.house;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.house.Floor;

public abstract interface FloorManager {
	public abstract void storeFloor(Floor paramFloor);

	public abstract List<Floor> loadAllFloor(Long[] paramArrayOfLong);

	public abstract void deleteAllFloor(List<Floor> paramList);

	public abstract Floor loadFloor(Long paramLong);

	public abstract void deleteFloor(Floor paramFloor);

	public abstract List<Floor> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<Floor> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;

	public abstract List<Floor> loadAllFloor();
}
